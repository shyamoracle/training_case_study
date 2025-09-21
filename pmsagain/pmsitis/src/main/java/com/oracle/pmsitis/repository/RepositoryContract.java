package com.oracle.pmsitis.repository;


import java.util.List;

public interface RepositoryContract<TModel,TPKey> {
	TModel insert(TModel data) throws Exception;
	TModel remove(TPKey id) throws Exception;
	TModel modify(TPKey id, TModel data) throws Exception;
	TModel get(TPKey id) throws Exception;
	List<TModel> getAll() throws Exception;
}