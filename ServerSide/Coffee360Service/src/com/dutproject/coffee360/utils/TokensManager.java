package com.dutproject.coffee360.utils;

import java.util.HashMap;
import java.util.Map;

import com.dutproject.coffee360.model.bean.Account;

public final class TokensManager {
	private static TokensManager instance = null;
	private Map<String, TokenExtraInfo> tokensHolder = new HashMap<>();
	private Object lock = new Object();

	public static TokensManager getInstance() {
		if (instance == null)
			instance = new TokensManager();
		return instance;
	}
	
	private String getTokenIfExists(Account account) {
		synchronized (lock) {
			for(String token : tokensHolder.keySet()){
				TokenExtraInfo extraInfo = tokensHolder.get(token);
				if (extraInfo.accountId == account.getId())
					return token;
			}
			return null;
		}
	}
	
	private String generateToken(Account account) {
		String token = SecuredTokenFactory.generateSecuredToken();
		TokenExtraInfo extraInfo = new TokenExtraInfo();
		extraInfo.setAccountId(account.getId());
		extraInfo.setLastCheckpoint(System.currentTimeMillis());
		synchronized (lock) {
			tokensHolder.put(token, extraInfo);
		}
		return token;
	}

	public String issueToken(Account account) {
		String token = getTokenIfExists(account);
		return token != null ? token : generateToken(account);
	}
	
	public void validateToken(String token) throws Exception {
		synchronized (lock) {
			TokenExtraInfo extraInfo = tokensHolder.get(token);
			if (extraInfo == null)
				throw new Exception();
		}
	}
	
	public int getAccountIdByToken(String token) {
		synchronized (lock) {
			TokenExtraInfo extraInfo = tokensHolder.get(token);
			return extraInfo != null ? extraInfo.getAccountId() : -1;
		}
	}

	private TokensManager() {
		TokenExtraInfo extraInfo = new TokenExtraInfo();
		extraInfo.setAccountId(12);
		synchronized (lock) {
			tokensHolder.put("temptoken", extraInfo);
			
			extraInfo = new TokenExtraInfo();
			extraInfo.setAccountId(2);
			tokensHolder.put("sonsapdeptrai", extraInfo);
		}
	}

	private class TokenExtraInfo {
		private int accountId;
		private long lastCheckpoint;

		public int getAccountId() {
			return accountId;
		}

		public void setAccountId(int accountId) {
			this.accountId = accountId;
		}

		public long getLastCheckpoint() {
			return lastCheckpoint;
		}

		public void setLastCheckpoint(long lastCheckpoint) {
			this.lastCheckpoint = lastCheckpoint;
		}

		public boolean isExpired() {
			return false;
		}
	}

}
