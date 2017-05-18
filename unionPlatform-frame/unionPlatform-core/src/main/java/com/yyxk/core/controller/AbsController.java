package com.yyxk.core.controller;

import java.io.Serializable;

import com.yyxk.core.service.AbsService;

/**
 * Controller基类.
 * 
 * @author Houkm
 *
 *         2017年5月5日
 * @param <T>
 * @param <ID>
 */
public abstract class AbsController<T, ID extends Serializable> {

	public abstract AbsService<T, ID> getService();

}
