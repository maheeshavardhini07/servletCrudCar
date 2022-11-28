package entity;

public class Car {

	private int id;
	private String brand;
	private String model;
	private int price;
	
	public Car(int id, String brand, String model, int price) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.price = price;
	}
	
	public Car(String brand, String model, int price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }
	

	public Car(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Car [Id= " + id + ", Brand= " + brand + ", Model= " + model + ", Price= " + price + "]";
	}
	
	
}
