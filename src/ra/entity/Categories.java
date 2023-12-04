package ra.entity;

import java.util.Scanner;

public class Categories {
    private int catalogId;
    private String catalogName;
    private String descriptions;
    private Boolean catalogStatus;

    public Categories() {
    }

    public Categories(int catalogId, String catalogName, String descriptions, Boolean catalogStatus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.descriptions = descriptions;
        this.catalogStatus = catalogStatus;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Boolean getCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(Boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    public void inputData(Scanner scanner, Categories[] arrCategories, int currentIndex) {
//        CatalogId
        if (currentIndex == 0) {
            this.catalogId = 1;
        } else {
            int maxCategoryId = arrCategories[0].getCatalogId();
            for (int i = 1; i < currentIndex; i++) {
                if (arrCategories[i].getCatalogId() > maxCategoryId) {
                    maxCategoryId = arrCategories[i].getCatalogId();
                }
            }
            this.catalogId = maxCategoryId + 1;
        }

//        catalogname
        boolean isExit = true;
        do {
            System.out.println("nhập tên danh mục:");
            String inputCatalogName = scanner.nextLine();

            if (inputCatalogName.length() < 50) {
                boolean nameExit = false;

                for (int i = 0; i < currentIndex; i++) {
                    if (arrCategories[i].getCatalogName().equals(inputCatalogName)) {
                        System.out.println("tên đã tồn tại");
                        nameExit = true;
                        break;
                    }
                }

                if (!nameExit) {
                    this.catalogName = inputCatalogName;
                    isExit = false;
                }
            } else {
                System.out.println("tên danh mục không được quá 50 kí tự");
            }

        } while (isExit);

//        description
        System.out.println("nhập mô tả danh mục:");
        this.descriptions = scanner.nextLine();

//        catalogStatus
        do {
            System.out.println("nhập trạng thái hoạt đông của danh mục:");
            String inputStatus = scanner.nextLine();

            if (inputStatus.equals("true") || inputStatus.equals("false")) {
                this.catalogStatus = Boolean.parseBoolean(inputStatus);
                break;
            } else if (inputStatus.equals("")) {
                this.catalogStatus = false;
                break;
            } else {
                System.out.println("Giá trị không hợp lệ. Vui lòng nhập lại.");
            }

        } while (true);
    }

    public void displayData() {
        System.out.printf("mã danh mục: %d, tên danh mục: %s\n", this.catalogId, this.catalogName);
        System.out.printf("mô tả danh mục: %s, trạng thái của danh mục: %b\n", this.descriptions, this.catalogStatus);
    }

    public void updateData(Scanner scanner, Categories[] arrCategories, int currentIndex) {
        boolean isExit = true;
        do {
            System.out.println("nhập tên danh mục thay đổi:");
            String updateName = scanner.nextLine();

            if (updateName.length() < 50) {
                boolean nameExit = false;

                for (int i = 0; i < currentIndex; i++) {
                    if (arrCategories[i].getCatalogName().equals(updateName)) {
                        System.out.println("tên đã tồn tại");
                        nameExit = true;
                        break;
                    }
                }

                if (!nameExit) {
                    this.catalogName = updateName;
                    isExit = false;

                }
            } else {
                System.out.println("tên danh mục không được quá 50 kí tự");
            }

        } while (isExit);

        System.out.println("nhập mô tả danh mục cần thay đổi:");
        this.descriptions = scanner.nextLine();


        do {
            System.out.println("nhập trạng thái cập nhật của danh mục:");
            String inputStatus = scanner.nextLine();

            if (inputStatus.equals("true") || inputStatus.equals("false")) {
                this.catalogStatus = Boolean.parseBoolean(inputStatus);
                break;
            } else if (inputStatus.equals("")) {
                this.catalogStatus = false;
                break;
            } else {
                System.out.println("Giá trị không hợp lệ. Vui lòng nhập lại.");
            }

        } while (true);
    }
}
