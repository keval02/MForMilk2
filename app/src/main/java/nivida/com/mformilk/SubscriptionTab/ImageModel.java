package nivida.com.mformilk.SubscriptionTab;

/**
 * Created by prince on 8/18/2017.
 */

public class ImageModel {

    private String name="";
    private int image_drawable;
    String itemCategoryName="";
    String itemPrice="";
    String totalPrice="0";
    int itemId=0;
    int itemCategoryId=0;
    boolean itemIsDeleted=true;
    String itemTaxId="";
    String itemCode="";
    int itemQuantity=0;
    int itemStatus=0;
    String itemShortName="";
    boolean itemIsActive=true;
    String itemDescription="";
    int totalQty=0;
    String date="";
    String status="";
    String amount="";

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getItemCategoryName() {
        return itemCategoryName;
    }

    public void setItemCategoryName(String itemCategoryName) {
        this.itemCategoryName = itemCategoryName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(int itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }

    public boolean isItemIsDeleted() {
        return itemIsDeleted;
    }

    public void setItemIsDeleted(boolean itemIsDeleted) {
        this.itemIsDeleted = itemIsDeleted;
    }

    public String getItemTaxId() {
        return itemTaxId;
    }

    public void setItemTaxId(String itemTaxId) {
        this.itemTaxId = itemTaxId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public int getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(int itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getItemShortName() {
        return itemShortName;
    }

    public void setItemShortName(String itemShortName) {
        this.itemShortName = itemShortName;
    }

    public boolean isItemIsActive() {
        return itemIsActive;
    }

    public void setItemIsActive(boolean itemIsActive) {
        this.itemIsActive = itemIsActive;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage_drawable() {
        return image_drawable;
    }

    public void setImage_drawable(int image_drawable) {
        this.image_drawable = image_drawable;
    }
}