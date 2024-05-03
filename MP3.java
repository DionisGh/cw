public class MP3 extends Gadget {
    
    private int availableMemory;
    
    public MP3(String model, double price, int weight, String size, int availableMemory) {
        super(model, price, weight, size);
        this.availableMemory = availableMemory;
    }

    public int getAvailableMemory() {
        return availableMemory;
    }

    public boolean downloadMusic(int memoryUsed) {
        if (memoryUsed <= availableMemory) {
            availableMemory -= memoryUsed;
            return true;
        } else {
            return false;
        }
    }

    public void deleteMusic(int memoryFreed) {
        availableMemory += memoryFreed;
        System.out.println(memoryFreed + " MB of memory freed.");
    }

    
}
