package com.speech.up.board.service.checkValue;

import org.springframework.data.domain.Page;

import com.speech.up.board.entity.BoardEntity;
import com.speech.up.common.exception.http.BadRequestException;

import lombok.Getter;

@Getter
public class CheckListForPagination {
	private final Page<BoardEntity> boardList;

	public CheckListForPagination(final Page<BoardEntity> boardList)  {
		if(boardList.isEmpty()){
			throw new BadRequestException("has no board! change value the size or page");
		}
		this.boardList = boardList;
	}
	public static CheckListForPagination checkListIsNotEmpty(final Page<BoardEntity> boardList) {
		return new CheckListForPagination(boardList);
	}
}
