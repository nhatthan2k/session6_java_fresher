package ra.run;

import ra.entity.Categories;
import ra.entity.Product;

import java.util.Scanner;

public class ShopManagement {
    Categories[] arrCategories = new Categories[100];
    Product[] arrProducts = new Product[100];

    int currentIndex = 0;

    int currentprodIndex = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShopManagement shopManagement = new ShopManagement();

        do {
            System.out.println("******************SHOP MENU*******************");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.println("lựa chọn của bạn: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    shopManagement.displayCategories(scanner, shopManagement);
                    break;
                case 2:
                    shopManagement.displayProduct(scanner, shopManagement);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("nhập lựa chọn từ 1-3");
            }
        } while (true);
    }

    public void displayCategories(Scanner scanner, ShopManagement shopManagement) {
        boolean isExit = true;

        do {
            System.out.println("****************CATEGORIES MENU***********");
            System.out.println("1. Nhập thông tin các danh mục");
            System.out.println("2. Hiển thị thông tin các danh mục");
            System.out.println("3. Cập nhật thông tin danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Cập nhật trạng thái danh mục");
            System.out.println("6. Thoát");
            System.out.println("lựa chọn của ban: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    shopManagement.inputCategorise(scanner);
                    break;
                case 2:
                    for (int i = 0; i < currentIndex; i++) {
                        arrCategories[i].displayData();
                    }
                    break;
                case 3:
                    shopManagement.updateCategorise(scanner);
                    break;
                case 4:
                    shopManagement.deleteCategories(scanner);
                    break;
                case 5:
                    shopManagement.updateCatalogStatus(scanner);
                    break;
                case 6:
                    isExit = false;
                default:
                    System.out.println("hãy chon 1 trong 6 lựa chọn trên");
            }
        } while (isExit);
    }

    public void inputCategorise(Scanner scanner) {
        System.out.println("nhập số danh mục muốn nhập: ");
        int numbook = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numbook; i++) {
            System.out.printf("nhập thông tin danh mục thứ %d: \n", i + 1);
            arrCategories[currentIndex] = new Categories();
            arrCategories[currentIndex].inputData(scanner, arrCategories, currentIndex);
            currentIndex++;
        }
    }

    public void updateCategorise(Scanner scanner) {
        System.out.println("nhập mã danh mục cần thay đổi:");
        int updateId = Integer.parseInt(scanner.nextLine());
        boolean isUpdate = false;
        int updateIndex = -1;

        for (int i = 0; i < currentIndex; i++) {
            if (arrCategories[i].getCatalogId() == updateId) {
                isUpdate = true;
                updateIndex = i;
                break;
            }
        }

        if (isUpdate) {
            arrCategories[updateIndex].updateData(scanner, arrCategories, currentIndex);
        } else {
            System.out.println("mã danh mục cập nhật không tồn tại");
        }
    }

    public void deleteCategories(Scanner scanner) {
        System.out.println("nhâp mã danh mục cân xóa: ");
        int deleteId = Integer.parseInt(scanner.nextLine());
        boolean isDelete = false;
        int deleteIndex = -1;

        for (int i = 0; i < currentIndex; i++) {
            if (arrCategories[i].getCatalogId() == deleteId) {
                isDelete = true;
                deleteIndex = i;
                break;
            }
        }

        if (isDelete) {
            Categories[] newArrCategories = new Categories[arrCategories.length - 1];

            for (int i = 0; i < deleteIndex; i++) {
                newArrCategories[i] = arrCategories[i];
            }

            for (int i = deleteIndex; i < currentIndex - 1; i++) {
                newArrCategories[i] = arrCategories[i + 1];
            }

            arrCategories = newArrCategories;
            currentIndex -= 1;
            System.out.println("xóa thành công!");
        } else {
            System.out.println("không tồn tại mã danh mục cần xóa!");
        }
    }

    public void updateCatalogStatus(Scanner scanner) {
        System.out.println("nhập mã danh mục cần cập nhật trạng thái");
        int updateStatusId = Integer.parseInt(scanner.nextLine());
        boolean isUpdate = false;
        int updateIndex = -1;

        for (int i = 0; i < currentIndex; i++) {
            if (arrCategories[i].getCatalogId() == updateStatusId) {
                isUpdate = true;
                updateIndex = i;
                break;
            }
        }

        if (isUpdate) {
            arrCategories[updateIndex].setCatalogStatus(!arrCategories[updateIndex].getCatalogStatus());
            System.out.println("cập nhật thành công!");
        } else {
            System.out.println("mã danh mục cập nhật không tồn tại");
        }
    }

    public void displayProduct(Scanner scanner, ShopManagement shopManagement) {
        boolean isExit = true;
        do {
            System.out.println("*******************PRODUCT MANAGEMENT*****************");
            System.out.println("1. Nhập thông tin các sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Sắp xếp các sản phẩm theo giá");
            System.out.println("4. Cập nhật thông tin sản phẩm theo mã sản phẩm");
            System.out.println("5. Xóa sản phẩm theo mã sản phẩm");
            System.out.println("6. Tìm kiếm các sản phẩm theo tên sản phẩm");
            System.out.println("7. Tìm kiếm sản phẩm trong khoảng giá a – b (a,b nhập từ bàn phím)");
            System.out.println("8.Thoát");
            System.out.println("lựa chon của bạn");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    shopManagement.inputProduct(scanner);
                    break;
                case 2:
                    for (int i = 0; i < currentprodIndex; i++) {
                        arrProducts[i].displayData();
                    }
                    break;
                case 3:
                    for (int i = 0; i < currentprodIndex - 1; i++) {
                        for (int j = 1; j < currentprodIndex; j++) {
                            if (arrProducts[i].getPrice() > arrProducts[j].getPrice()) {
                                Product temp = arrProducts[i];
                                arrProducts[i] = arrProducts[j];
                                arrProducts[j] = temp;
                            }
                        }
                    }

                    System.out.println("đã sắp xếp sản phẩm theo giá tăng dần");
                    break;
                case 4:
                    shopManagement.updateProduct(scanner);
                    break;
                case 5:
                    shopManagement.deleteProduct(scanner);
                    break;
                case 6:
                    System.out.println("nhập tên sản phẩm muốn tìm:");
                    String findName = scanner.nextLine();
                    boolean isFindName = false;
                    int findNameIndex = -1;

                    for (int i = 0; i < currentprodIndex; i++) {
                        if (arrProducts[i].getProductName().equals(findName)) {
                            isFindName = true;
                            findNameIndex = i;
                            break;
                        }
                    }

                    if (isFindName) {
                        arrProducts[findNameIndex].displayData();
                    } else {
                        System.out.println("tên bạn tìm kiếm không tồn tại!");
                    }
                    break;
                case 7:
                    shopManagement.findPriceProduct(scanner);
                    break;
                case 8:
                    isExit = false;
                default:
                    System.out.println("nhập lựa chon của ban từ 1-8!");
            }

        } while (isExit);
    }

    public void inputProduct(Scanner scanner) {
        System.out.println("nhập số sản phảm cần nhập:");
        int numProduct = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numProduct; i++) {
            System.out.printf("nhập thông tin sản phẩm thứ %d: \n", i + 1);
            arrProducts[currentprodIndex] = new Product();
            arrProducts[currentprodIndex].inputData(scanner, arrProducts, currentprodIndex, arrCategories, currentIndex);
            currentprodIndex++;
        }
    }

    public void updateProduct(Scanner scanner) {
        System.out.println("nhập mã sản phẩm cập nhật:");
        String updateProductId = scanner.nextLine();
        boolean isUpdate = false;
        int updateIndex = -1;

        for (int i = 0; i < currentprodIndex; i++) {
            if (arrProducts[i].getProductid().equals(updateProductId)) {
                isUpdate = true;
                updateIndex = i;
                break;
            }
        }
        if (isUpdate) {
            arrProducts[updateIndex].inputData(scanner, arrProducts, currentprodIndex, arrCategories, currentIndex);
        } else {
            System.out.println("mã sản phẩm không tồn tại");
        }
    }

    public void deleteProduct(Scanner scanner) {
        System.out.println("nhập mã sản phẩm xóa: ");
        String deleteProductId = scanner.nextLine();
        boolean isDeleteProd = false;
        int deleteProIndex = -1;

        for (int i = 0; i < currentprodIndex; i++) {
            if (arrProducts[i].getProductid().equals(deleteProductId)) {
                isDeleteProd = true;
                deleteProIndex = i;
                break;
            }
        }

        if (isDeleteProd) {
            Product[] arrDeleteProduct = new Product[100];
            for (int i = 0; i < deleteProIndex; i++) {
                arrDeleteProduct[i] = arrProducts[i];
            }

            for (int i = deleteProIndex; i < currentprodIndex - 1; i++) {
                arrDeleteProduct[i] = arrProducts[i + 1];
            }

            arrProducts = arrDeleteProduct;
            currentprodIndex -= 1;
        } else {
            System.out.println("không tồn tại mã sản phẩm!");
        }
    }

    public void findPriceProduct(Scanner scanner) {
        System.out.println("nhập khoảng giá bạn muốn tìm kiếm: ");
        System.out.println("giá từ:");
        float a = Float.parseFloat(scanner.nextLine());
        System.out.println("đến:");
        float b = Float.parseFloat(scanner.nextLine());

        System.out.printf("Sản phẩm trong khoảng giá từ %f đến %f là: \n", a, b);
        for (int i = 0; i < currentprodIndex; i++) {
            if (arrProducts[i].getPrice() > a && arrProducts[i].getPrice() < b) {
                arrProducts[i].displayData();
            }
        }
    }
}
