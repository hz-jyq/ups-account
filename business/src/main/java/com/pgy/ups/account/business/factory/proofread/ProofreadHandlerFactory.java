package com.pgy.ups.account.business.factory.proofread;

import java.io.InputStream;

import com.pgy.ups.account.business.factory.interfaces.BusinessFactory;
import com.pgy.ups.account.business.handler.proofread.ProofreadHandler;

interface ProofreadHandlerFactory<T,R> extends BusinessFactory<ProofreadHandler<T,R>> {

	public ProofreadHandler<String, InputStream> getProofreadHandler() ;
		
}
