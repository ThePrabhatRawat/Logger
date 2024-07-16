import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductCatalog {
    private List<Product> products;

    // Constructor
    public ProductCatalog() {
        products = new ArrayList<>();
        loadFromFile("products.dat");
    }

    // Add a product
    public void addProduct(Product product) throws Exception {
        if (isDuplicate(product)) {
            throw new Exception("Duplicate product found! Name and category must be unique.");
        }
        products.add(product);
        saveToFile("products.dat");
    }

    // Check for duplicate product by name and category
    private boolean isDuplicate(Product product) {
        return products.stream().anyMatch(p -> p.getName().equalsIgnoreCase(product.getName()) && p.getCategory().equalsIgnoreCase(product.getCategory()));
    }

    // View all products
    public List<Product> viewProducts() {
        return products;
    }

    // Search for a product by name or subsequence
    public List<Product> searchProduct(String subsequence) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(subsequence.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }

    // Update a product
    public void updateProduct(Product updatedProduct) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(updatedProduct.getName())) {
                product.setCategory(updatedProduct.getCategory());
                product.setPrice(updatedProduct.getPrice());
                product.setQuantity(updatedProduct.getQuantity());
                saveToFile("products.dat");
                break;
            }
        }
    }

    // Delete a product by name
    public void deleteProduct(String name) {
        products.removeIf(product -> product.getName().equalsIgnoreCase(name));
        saveToFile("products.dat");
    }

    // Sort products by name in ascending order
    public void sortProductsByNameAsc() {
        products.sort(Comparator.comparing(Product::getName));
    }

    // Sort products by name in descending order
    public void sortProductsByNameDesc() {
        products.sort((p1, p2) -> p2.getName().compareTo(p1.getName()));
    }

    // Sort products by price in ascending order
    public void sortProductsByPriceAsc() {
        products.sort(Comparator.comparingDouble(Product::getPrice));
    }

    // Sort products by price in descending order
    public void sortProductsByPriceDesc() {
        products.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
    }

    // Save products to a file
    private void saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(products);
        } catch (IOException e) {
            System.err.println("Error saving products to file: " + e.getMessage());
        }
    }

    // Load products from a file
    @SuppressWarnings("unchecked")
    private void loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            products = (List<Product>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            products = new ArrayList<>();
            System.err.println("Error loading products from file: " + e.getMessage());
        }
    }
}
