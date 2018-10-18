package com.pgy.ups.account.business.factory.proofread;

import java.io.InputStream;
import java.util.List;

import com.pgy.ups.account.business.handler.proofread.DocumentParserHandler;
import com.pgy.ups.account.business.handler.proofread.ProofreadHandler;
import com.pgy.ups.account.facade.model.proofread.BussinessProofreadModel;
import com.pgy.ups.account.facade.model.proofread.ProofreadResult;

public class BaofuProofreadHandlerFactory implements ProofreadHandlerFactory<InputStream, String> {

	@Override
	public ProofreadHandler<String, InputStream> getProofreadHandler() {
		return new BaoFuProofreadHandler(new BaoFuDocumentParserHandler());
	}

}

class BaoFuProofreadHandler implements ProofreadHandler<String, InputStream> {

	private DocumentParserHandler<String, InputStream> documentParserHandler;

	public BaoFuProofreadHandler(DocumentParserHandler<String, InputStream> documentParserHandler) {
		this.documentParserHandler = documentParserHandler;
	}

	@Override
	public ProofreadResult handler(List<BussinessProofreadModel> t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDocumentParserHandler(DocumentParserHandler<String, InputStream> documentParserHandler) {
		// TODO Auto-generated method stub

	}
}

class BaoFuDocumentParserHandler implements DocumentParserHandler<String, InputStream> {

	@Override
	public InputStream handler(String t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDocumentName() {
		// TODO Auto-generated method stub
		return null;
	}

}
