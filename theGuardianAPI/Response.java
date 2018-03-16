package theGuardianAPI;

public class Response {

	String status;
	String userTier;
	int total;
	Article content;

	public Response() {
	}

	public Response(final String status, final String userTier, final int total, final Article content,
			final int startIndex, final int pageSize, final int currentPage, final int pages, final String orderBy,
			final Article[] results) {
		this.status = status;
		this.userTier = userTier;
		this.total = total;
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public String getUserTier() {
		return userTier;
	}

	public void setUserTier(final String userTier) {
		this.userTier = userTier;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(final int total) {
		this.total = total;
	}

	public Article getContent() {
		return content;
	}

	public void setContent(final Article content) {
		this.content = content;
	}

}
