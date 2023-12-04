package ra.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Product {
    private String productid;
    private String productName;
    private float price;
    private String description;
    private Date createdDate;
    private int catalogId;
    private int productStatus;

    public Product() {
    }

    public Product(String productid, String productName, float price, String description, Date createdDate, int catalogId, int productStatus) {
        this.productid = productid;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.createdDate = createdDate;
        this.catalogId = catalogId;
        this.productStatus = productStatus;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public void inputData(Scanner scanner, Product[] arrProducts, int currentProdIndex, Categories[] arrCategories, int currentIndex) {
//        productId
        boolean isExit = true;
        do {
            System.out.println("nhập mã sản phẩm có bắt đầu C, S, A: ");
            String inputProductId = scanner.nextLine();
            boolean isProduct = false;

            if (inputProductId.length() == 4 && (inputProductId.startsWith("S") || inputProductId.startsWith("C") || inputProductId.startsWith("A"))) {
                for (int i = 0; i < currentProdIndex; i++) {
                    if (arrProducts[i].getProductid().equals(inputProductId)) {
                        System.out.println("mã sản phẩm đã tồn tại");
                        isProduct = true;
                        break;
                    }
                }

                if (!isProduct) {
                    this.productid = inputProductId;
                    isExit = false;
                }

            } else {
                System.out.println("mã sản phẩm không hợp lệ! vui lòng nhập lại");
            }
        } while (isExit);

//        productName
        boolean isExitProdName = true;
        do {
            System.out.println("tên sản phẩm đồ uống: ");
            String inputProductName = scanner.nextLine();
            boolean isProductName = false;

            if (inputProductName.length() >= 10 && inputProductName.length() <= 50) {
                for (int i = 0; i < currentProdIndex; i++) {
                    if (arrProducts[i].getProductName().equals(inputProductName)) {
                        System.out.println("tên sản phẩm đã tồn tại");
                        isProductName = true;
                        break;
                    }
                }

                if (!isProductName) {
                    this.productName = inputProductName;
                    isExitProdName = false;
                }

            } else {
                System.out.println("tên sản phẩm đồ uống có từ 10-50 ký tự");
            }
        } while (isExitProdName);

//      Price
        boolean isExitProdPrice = true;
        do {
            System.out.println("nhập giá của sản phẩm: ");
            float Price = Float.parseFloat(scanner.nextLine());

            if (Price > 0) {
                this.price = Price;
                isExitProdPrice = false;
            } else {
                System.out.println("giá của sản phẩm phải lớn hơn 0");
            }

        } while (isExitProdPrice);

        System.out.println("nhập mô tả sản phẩm: ");
        this.description = scanner.nextLine();

        System.out.println("nhập ngày nhập sản phẩm: ");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.createdDate = sdf.parse(scanner.nextLine());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        System.out.println("***** danh sách danh mục sản phẩm *****");
        if (currentIndex == 0) {
            System.out.println("chưa có danh mục sản phẩm nào!");
        } else {
            for (int i = 0; i < currentIndex; i++) {
                arrCategories[i].displayData();
            }
        }
        System.out.println("nhập Mã danh mục mà sản phẩm thuộc về: ");
        this.catalogId = Integer.parseInt(scanner.nextLine());

        do {
            System.out.println("nhập trạng thái của sản phẩm (0: Đang bán – 1: Hết hàng – 2: Không bán): ");
            int productStatus = Integer.parseInt(scanner.nextLine());

            if (productStatus == 0 || productStatus == 1 || productStatus == 2) {
                this.productStatus = productStatus;
                break;
            } else {
                System.out.println("trạng thái sản phẩm không hợp lệ! vui lòng nhập lại");
            }
        } while (true);
    }

    public void displayData() {
        System.out.printf("mã sản phẩm: %s, tên sản phẩm: %s, giá sản phẩm: %f\n", this.productid, this.productName, this.price);
        System.out.printf("mô tả: %s, ngày tạo: %s, mã danh mục: %d, trang thái sản phẩm: %d\n", this.description, this.createdDate, this.catalogId, this.productStatus);
    }
}
