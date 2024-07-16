import java.util.List;
import java.util.Scanner;

public class ProductCatalogManager {
    private ProductCatalog catalog;
    private Scanner scanner;
    private boolean authenticated;
    private boolean isAdmin;

    // Constructor
    public ProductCatalogManager() {
        catalog = new ProductCatalog();
        scanner = new Scanner(System.in);
        authenticated = false;
        isAdmin = false;
    }

    // Display admin menu
    private void displayAdminMenu() {
        System.out.println("\nProduct Catalog Management System (Admin)");
        System.out.println("1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. Search Product");
        System.out.println("4. Update Product");
        System.out.println("5. Delete Product");
        System.out.println("6. Sort Products by Name (Asc)");
        System.out.println("7. Sort Products by Name (Desc)");
        System.out.println("8. Sort Products by Price (Asc)");
        System.out.println("9. Sort Products by Price (Desc)");
        System.out.println("10. Exit");
        System.out.print("Enter your choice: ");
    }

    // Display user menu
    private void displayUserMenu() {
        System.out.println("\nProduct Catalog Management System");
        System.out.println("1. View Products");
        System.out.println("2. Search Product");
        System.out.println("3. Purchase Product");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    // Handle admin input
    private void handleAdminInput() {
        try {
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    viewProducts();
                    break;
                case 3:
                    searchProduct();
                    break;
                case 4:
                    updateProduct();
                    break;
                case 5:
                    deleteProduct();
                    break;
                case 6:
                    sortProductsByNameAsc();
                    break;
                case 7:
                    sortProductsByNameDesc();
                    break;
                case 8:
                    sortProductsByPriceAsc();
                    break;
                case 9:
                    sortProductsByPriceDesc();
                    break;
                case 10:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    pause();
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            pause();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            pause();
        }
    }

    // Handle user input
    private void handleUserInput() {
        try {
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    viewProducts();
                    break;
                case 2:
                    searchProduct();
                    break;
                case 3:
                    purchaseProduct();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    pause();
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            pause();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            pause();
        }
    }

    // Add a product
    private void addProduct() {
        try {
            System.out.print("Enter product name: ");
            String name = scanner.nextLine();
            System.out.print("Enter product category: ");
            String category = scanner.nextLine();
            System.out.print("Enter product price: ");
            double price = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter product quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            Product product = new Product(name, category, price, quantity);
            catalog.addProduct(product);
            System.out.println("Product added successfully.");
            pause();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Price and quantity should be numeric.");
            pause();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            pause();
        }
    }

    // View all products
    private void viewProducts() {
        List<Product> products = catalog.viewProducts();
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
        System.out.println("\nPress Enter to return to the menu.");
        scanner.nextLine();
    }

    // Search for a product by subsequence
    private void searchProduct() {
        System.out.print("Enter product name or subsequence to search: ");
        String subsequence = scanner.nextLine();
        List<Product> results = catalog.searchProduct(subsequence);

        if (results.isEmpty()) {
            System.out.println("No products found.");
        } else {
            System.out.println("Search results:");
            for (Product product : results) {
                System.out.println(product);
            }
        }
        System.out.println("\nPress Enter to return to the menu.");
        scanner.nextLine();
    }

    // Update a product
    private void updateProduct() {
        try {
            System.out.print("Enter product name to update: ");
            String name = scanner.nextLine();
            Product existingProduct = catalog.searchProduct(name).stream().findFirst().orElse(null);

            if (existingProduct != null) {
                System.out.print("Enter new category: ");
                String category = scanner.nextLine();
                System.out.print("Enter new price: ");
                double price = Double.parseDouble(scanner.nextLine());
                System.out.print("Enter new quantity: ");
                int quantity = Integer.parseInt(scanner.nextLine());

                Product updatedProduct = new Product(name, category, price, quantity);
                catalog.updateProduct(updatedProduct);
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("Product not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Price and quantity should be numeric.");
            pause();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            pause();
        }
    }

    // Delete a product
    private void deleteProduct() {
        try {
            System.out.print("Enter product name to delete: ");
            String name = scanner.nextLine();
            catalog.deleteProduct(name);
            System.out.println("Product deleted successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            pause();
        }
    }

    // Sort products by name in ascending order
    private void sortProductsByNameAsc() {
        catalog.sortProductsByNameAsc();
        System.out.println("Products sorted by name (Asc).");
        viewProducts();
    }

    // Sort products by name in descending order
    private void sortProductsByNameDesc() {
        catalog.sortProductsByNameDesc();
        System.out.println("Products sorted by name (Desc).");
        viewProducts();
    }

    // Sort products by price in ascending order
    private void sortProductsByPriceAsc() {
        catalog.sortProductsByPriceAsc();
        System.out.println("Products sorted by price (Asc).");
        viewProducts();
    }

    // Sort products by price in descending order
    private void sortProductsByPriceDesc() {
        catalog.sortProductsByPriceDesc();
        System.out.println("Products sorted by price (Desc).");
        viewProducts();
    }

    // Purchase a product
    private void purchaseProduct() {
        viewProducts();

        try {
            System.out.print("Enter the name of the product you want to purchase: ");
            String name = scanner.nextLine();
            System.out.print("Enter the category of the product you want to purchase: ");
            String category = scanner.nextLine();
            System.out.print("Enter the quantity you want to purchase: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            List<Product> results = catalog.searchProduct(name);
            Product productToPurchase = null;
            for (Product product : results) {
                if (product.getCategory().equalsIgnoreCase(category)) {
                    productToPurchase = product;
                    break;
                }
            }

            if (productToPurchase != null) {
                if (productToPurchase.getQuantity() >= quantity) {
                    productToPurchase.setQuantity(productToPurchase.getQuantity() - quantity);
                    catalog.updateProduct(productToPurchase);
                    System.out.println("Product purchased successfully.");
                } else {
                    System.out.println("Not enough stock available.");
                }
            } else {
                System.out.println("Product not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Quantity should be numeric.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        pause();
    }

    // Authenticate user
    private boolean authenticate() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Simple hardcoded authentication
        if ("admin".equals(username) && "password".equals(password)) {
            isAdmin = true;
            return true;
        } else if ("user".equals(username) && "password".equals(password)) {
            isAdmin = false;
            return true;
            } else {
            return false;
            }
            }

            // Pause to allow user to read messages
private void pause() {
    System.out.println("Press Enter to continue...");
    scanner.nextLine();
}

// Main method
public void run() {
    if (authenticate()) {
        authenticated = true;
        System.out.println("Authentication successful.");

        while (true) {
            if (isAdmin) {
                displayAdminMenu();
                handleAdminInput();
            } else {
                displayUserMenu();
                handleUserInput();
            }
        }
    } else {
        System.out.println("Authentication failed. Exiting...");
        pause();
        System.exit(0);
    }
}

public static void main(String[] args) {
    ProductCatalogManager manager = new ProductCatalogManager();
    manager.run();
}
}
