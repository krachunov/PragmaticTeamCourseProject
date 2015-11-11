package BookStore;

public class Permissions {
	private boolean canSellProduct = false;
	private boolean canCreateNewProduct = false;
	private boolean canModifyProduct = false;
	private boolean canRemoveProduct = false;
	
	public void makeAdmin() {
		this.setCanCreateNewProduct(true);
		this.setCanModifyProduct(true);
		this.setCanRemoveProduct(true);
		this.setCanSellProduct(true);

	}
	
	public void makeSalesEmployee() {
		this.setCanCreateNewProduct(false);
		this.setCanModifyProduct(false);
		this.setCanRemoveProduct(false);
		this.setCanSellProduct(true);

	}

	public boolean isCanSellProduct() {
		return canSellProduct;
	}

	public void setCanSellProduct(boolean canSellProduct) {
		this.canSellProduct = canSellProduct;
	}

	public boolean isCanCreateNewProduct() {
		return canCreateNewProduct;
	}

	public void setCanCreateNewProduct(boolean canCreateNewProduct) {
		this.canCreateNewProduct = canCreateNewProduct;
	}

	public boolean isCanModifyProduct() {
		return canModifyProduct;
	}

	public void setCanModifyProduct(boolean canModifyProduct) {
		this.canModifyProduct = canModifyProduct;
	}

	public boolean isCanRemoveProduct() {
		return canRemoveProduct;
	}

	public void setCanRemoveProduct(boolean canRemoveProduct) {
		this.canRemoveProduct = canRemoveProduct;
	}


}
