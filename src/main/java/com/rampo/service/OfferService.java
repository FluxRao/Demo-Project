package com.rampo.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rampo.entity.Item;
import com.rampo.entity.ItemOfferConfig;
import com.rampo.entity.ItemUserMap;
import com.rampo.entity.Offer;
import com.rampo.entity.OfferUserMap;
import com.rampo.entity.ShopUserMap;
import com.rampo.model.ItemDTO;
import com.rampo.model.OfferDTO;
import com.rampo.model.ShopDTO;
import com.rampo.model.input.OfferIdInput;
import com.rampo.model.input.OfferSaveInput;
import com.rampo.model.input.pagination.PaginationInput;
import com.rampo.model.input.pagination.PaginationWithoutPageInput;
import com.rampo.model.output.pagination.OfferPaginationOutput;
import com.rampo.repository.ItemOfferConfigRepo;
import com.rampo.repository.ItemRepository;
import com.rampo.repository.ItemUserMapRepository;
import com.rampo.repository.OfferRepository;
import com.rampo.repository.OfferUserMapRepository;
import com.rampo.repository.ShopUserMapRepository;
import com.rampo.util.ObjectMapper;

@Service
public class OfferService {

	@Autowired
	private OfferRepository offerRepo;

	@Autowired
	private ItemRepository itemRepo;

	@Autowired
	private ItemOfferConfigRepo ioRepo;

	@Autowired
	private ViewService viewService;

	@Autowired
	private OfferUserMapRepository offerUserMapRepo;

	@Autowired
	private ItemUserMapRepository itemUserMapRepo;

	@Autowired
	private ShopUserMapRepository shopUserMapRepo;

	public Set<OfferDTO> getAllOfferData(PaginationWithoutPageInput offerInput, String city, String userName) {
		Pageable pageable = PageRequest.of(0, 30);
		Page<Offer> offers = offerRepo.findOffersByCity(city, pageable);
//		Page<ItemOfferConfig> iocPage = ioRepo
//				.findByItem_Shop_ShopCityAndIsActiveTrueAndItem_IsActiveTrueAndOffer_IsActiveTrueAndItem_Shop_IsActiveTrue(
//						city, pageable);
//		List<Offer> offerList = new ArrayList<>();
//		for (ItemOfferConfig itemOfferConfig : iocPage) {
//			offerList.add(itemOfferConfig.getOffer());
//		}
		ZoneId zone = ZoneId.of("Asia/Kolkata");
		Set<OfferDTO> outputList = new HashSet<>();
		for (Offer offer : offers) {

			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate startDate = LocalDate.parse(offer.getStartDate(), dateFormatter);
			LocalDate endDate = LocalDate.parse(offer.getEndDate(), dateFormatter);

			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
			LocalTime startTime = LocalTime.parse(offer.getStartTime(), timeFormatter);
			LocalTime endTime = LocalTime.parse(offer.getEndTime(), timeFormatter);

			boolean flag = true;
			if (!endDate.isBefore(LocalDate.now(zone)) && !(startDate.isAfter(LocalDate.now(zone)))) {
				if (endDate.isEqual(LocalDate.now(zone))) {
					if (!endTime.isAfter(LocalTime.now(zone))) {
						flag = false;
					}
				}
				if (startDate.isEqual(LocalDate.now(zone))) {
					if (!startTime.isBefore(LocalTime.now(zone))) {
						flag = false;
					}
				}
				if (flag) {
					if (userName == null) {
						OfferDTO offerDTO = ObjectMapper.map(offer, OfferDTO.class);
						offerDTO.setDidLike(false);
						outputList.add(offerDTO);
					} else {
						OfferUserMap offerUserMap = offerUserMapRepo
								.findByOffer_OfferIdAndUser_UserName(offer.getOfferId(), userName);

						OfferDTO offerDTO = ObjectMapper.map(offer, OfferDTO.class);
						if (offerUserMap != null) {
							offerDTO.setDidLike(offerUserMap.isDidLike());
						} else {
							offerDTO.setDidLike(false);
						}
						outputList.add(offerDTO);
					}
				}
			}
		}
		return outputList;
	}

	public Set<OfferDTO> getOffersWhichAreOnLastDate(PaginationWithoutPageInput hurryUpInput, String userName,
			String city) throws ParseException {

		Sort sort = Sort.by(hurryUpInput.getSortDirection(), hurryUpInput.getSortBy());
		Pageable pageable = PageRequest.of(0, 10, sort);

		ZoneId zone = ZoneId.of("Asia/Kolkata");
		String endDate = LocalDate.now(zone).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		Page<Offer> offerPage = offerRepo.findByEndDate(city, endDate, pageable);
		Set<OfferDTO> outputList = new HashSet<>();
		for (Offer offer : offerPage) {

			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm").withLocale(Locale.ENGLISH);
			LocalTime startTime = LocalTime.parse(offer.getStartTime(), timeFormatter);
			LocalTime endTime = LocalTime.parse(offer.getEndTime(), timeFormatter);

			if (startTime.isBefore(LocalTime.now(zone)) && endTime.isAfter(LocalTime.now(zone))) {

				OfferUserMap oum = offerUserMapRepo.findByOffer_OfferIdAndUser_UserName(offer.getOfferId(), userName);
				OfferDTO offerDTO = ObjectMapper.map(offer, OfferDTO.class);
				if (oum != null) {
					offerDTO.setDidLike(oum.isDidLike());
				} else {
					offerDTO.setDidLike(false);
				}
				outputList.add(offerDTO);
			}
		}
		return outputList;
	}

	public Map<String, Object> findById(OfferIdInput input) {

		viewService.viewOffer(input.getOfferId(), input.getUserName());

		Map<String, Object> output = new HashMap<>();

		Offer offer = offerRepo.findById(input.getOfferId()).get();
		OfferDTO offerDTO = ObjectMapper.map(offer, OfferDTO.class);

		OfferUserMap oum = offerUserMapRepo.findByOffer_OfferIdAndUser_UserName(offerDTO.getOfferId(),
				input.getUserName());
		if (oum != null) {
			offerDTO.setDidLike(oum.isDidLike());
		} else {
			offerDTO.setDidLike(false);
		}
		output.put("offer", offerDTO);

		List<ItemOfferConfig> ioList = ioRepo.findByOffer_OfferIdAndIsActiveTrue(input.getOfferId());

		Set<ItemDTO> itemList = new HashSet<>();
		Set<ShopDTO> shopList = new HashSet<>();
		for (ItemOfferConfig itemOfferConfig : ioList) {

			Item item = itemOfferConfig.getItem();
			ItemDTO itemDTO = ObjectMapper.map(item, ItemDTO.class);

			ItemUserMap ium = itemUserMapRepo.findByItem_ItemIdAndUser_UserName(itemDTO.getItemId(),
					input.getUserName());
			if (ium != null) {
				itemDTO.setDidLike(ium.isDidLike());
			} else {
				itemDTO.setDidLike(false);
			}

			ShopDTO shopDTO = ObjectMapper.map(item.getShop(), ShopDTO.class);
			ShopUserMap sum = shopUserMapRepo.findByShop_ShopIdAndUser_UserName(shopDTO.getShopId(),
					input.getUserName());
			if (sum != null) {
				shopDTO.setDidLike(sum.isDidLike());
			} else {
				shopDTO.setDidLike(false);
			}

			itemList.add(itemDTO);
			shopList.add(shopDTO);
		}

		output.put("items", itemList);
		output.put("shop", shopList);

		return output;
	}

	public Object viewAllOffers(PaginationInput input) {

		Sort sort = Sort.by(input.getSortDirection(), input.getSortBy());
		Pageable pageable = PageRequest.of(input.getPageNumber(), input.getPageSize(), sort);
		Page<Offer> offers = offerRepo.findOffersByCity(input.getCity(), pageable);
		Set<OfferDTO> offerList = new HashSet<>();

		for (Offer offerEntity : offers.getContent()) {
			OfferDTO offerDTO = ObjectMapper.map(offerEntity, OfferDTO.class);

			OfferUserMap oum = offerUserMapRepo.findByOffer_OfferIdAndUser_UserName(offerDTO.getOfferId(),
					input.getUserName());
			if (oum != null) {
				offerDTO.setDidLike(oum.isDidLike());
			} else {
				offerDTO.setDidLike(false);
			}
			offerList.add(offerDTO);
		}

		OfferPaginationOutput output = new OfferPaginationOutput(offerList, offers.getTotalPages(),
				offers.getTotalElements(), offers.isLast(), offers.getNumber(), offers.getSize(),
				offers.getNumberOfElements());
		return output;
	}

	public Object saveOffer(OfferSaveInput input, String username) throws ParseException {

		Date date = new Date();

		Offer offer = ObjectMapper.map(input, Offer.class);
		offer.setStartDate(input.getStartingDate());
		offer.setEndDate(input.getEndingDate());
		offer.setStartTime(input.getStartingTime());
		offer.setEndTime(input.getEndingTime());
		offer.setCreatedOn(date);

		Offer offer1 = offerRepo.save(offer);

		for (Long itemId : input.getItemsIds()) {
			Item item = itemRepo.findById(itemId).get();

			ItemOfferConfig ioc = new ItemOfferConfig();
			ioc.setOffer(offer1);
			ioc.setItem(item);
			ioc.setActive(true);
			ioc.setCreatedOn(date);

			ioRepo.save(ioc);
		}
		return offer1;
	}
}