package com.ccp.vis.handler;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ccp.constantes.CcpConstants;
import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.exceptions.process.CcpFlow;
import com.ccp.fields.validations.exceptions.CcpJsonInvalid;

@RestControllerAdvice
public class VisSyncExceptionHandler {
//	private final JnSyncBusinessNotifyError notifyError = new JnSyncBusinessNotifyError();

	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler({ CcpJsonInvalid.class })
	public Map<String, Object> handle(CcpJsonInvalid e) {
		return e.errors.content;
	}

	@ExceptionHandler({ CcpFlow.class })
	@ResponseBody
	public Map<String, Object> handle(CcpFlow e, HttpServletResponse res) {

		res.setStatus(e.status);

		String message = e.getMessage();

		CcpJsonRepresentation result = CcpConstants.EMPTY_JSON.put("message", message);

		if (e.fields.length <= 0) {
			return result.content;
		}

		CcpJsonRepresentation subMap = e.values.getJsonPiece(e.fields);

		CcpJsonRepresentation putAll = result.putAll(subMap);

		return putAll.content;
	}

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ Throwable.class })
	public Map<String, Object> handle(Throwable e) {
//		return this.notifyError.apply(e);
		return null;
	}

	

}
