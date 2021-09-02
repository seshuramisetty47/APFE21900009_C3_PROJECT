import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
    	
    	LocalTime currentTime=getCurrentTime();
    	if(currentTime.compareTo(openingTime)>0 && currentTime.compareTo(closingTime)<0)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    

    public String getName() {
        return name;
    }
    
    public int getOrderValue(List<String> selectedItems)
    {
    	int orderValue=0;
    	for(String item:selectedItems)
    	{
    		Item newItem=findItemByName(item);
    		orderValue=orderValue+newItem.getPrice();
    	}    	    	
    	return orderValue;
    }

}
