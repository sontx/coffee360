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

	public String issueToken(Account account) {
		String token = SecuredTokenFactory.generateSecuredToken();
		TokenExtraInfo extraInfo = new TokenExtraInfo();
		extraInfo.setAccountId(account.getId());
		extraInfo.setLastCheckpoint(System.currentTimeMillis());
		synchronized (lock) {
			tokensHolder.put(token, extraInfo);
		}
		return token;
	}

	private TokensManager() {
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
