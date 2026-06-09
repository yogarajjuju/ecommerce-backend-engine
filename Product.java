public class Product{
     private  String id;
     private  String name;
    private double price;
     private  int stock;

     public Product(String id, String name , double price, int stock){
        this.id =id;
        this.name=name;
        this.price=price;
        this.stock=stock;
    
     }
     public String getId(){ return id;}
     public String getName(){ return name;}
     public double getPrice(){return price;}
     public int getStock(){return stock;}

    // Method to reduce stock when someone adds to cart
    public void reduceStock(int quantity) {
        if (this.stock < quantity) {
            // If they ask for more than we have, throw the custom error!
            throw new InsufficientStockException("Not enough stock for: " + this.name + ". Only " + this.stock + " left.");
        }
        this.stock -= quantity;
    }
     @Override
    public String toString() {
        return name + " ($" + price + ") - Stock: " + stock;
    }
}