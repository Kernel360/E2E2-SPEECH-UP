package com.speech.up.board.service.checkValue;

import com.speech.up.common.exception.http.BadRequestException;

import lombok.Getter;

@Getter
public class CheckParamForPagination {
	private final int page;
	private final int size;

	public CheckParamForPagination(final int page,final int size) {
		if(page <= 0 || size <= 0){
			throw new BadRequestException("page or size can not be less than zero");
		}
		if(size > 100) {
			throw new BadRequestException("size is must be less than 100.");
		}
		this.page = page;
		this.size = size;
	}


	public static CheckParamForPagination checkSizeAndPage(final int page, final int size)  {
		return new CheckParamForPagination(page, size);
	}


}
