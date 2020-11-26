package com.rampo.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

public class ObjectMapper {

	public static <S, T> void map(S source, T target) {
		if (source == null) {
			target = null;
			return;
		}

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		modelMapper.map(source, target);
	}

	public static <S, T> T map(S source, Type targetType) {

		if (source == null) {
			return null;
		}

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		return modelMapper.map(source, targetType);
	}

	static <S, T> T map(S source, Class<T> clazz) {

		if (source == null) {
			return null;
		}

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		return modelMapper.map(source, clazz);
	}

	public static <S, T> List<T> mapList(List<S> sourceList, Class<T> clazz) {

		if (sourceList == null)
			return null;

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		List<T> targetList = new ArrayList<>();

		if (sourceList != null) {
			for (S source : sourceList) {
				targetList.add(modelMapper.map(source, clazz));
			}
		}

		return targetList;
	}
}
