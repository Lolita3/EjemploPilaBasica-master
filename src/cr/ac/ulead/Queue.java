package cr.ac.ulead;

public class Queue {
    int[] reg_data = new int[480];
    int reg_head = 0;
    int reg_tail = 0;

    public void reg_enqueue(int newValue){
        this.reg_data[this.reg_tail] = newValue;
        this.reg_tail++;
    }

    public int reg_dequeue() {
        return this.reg_data[reg_head++];
    }



    int[] esp_data = new int[480];
    int esp_head = 0;
    int esp_tail = 0;

    public void esp_enqueue(int newValue){
        this.esp_data[this.esp_tail] = newValue;
        this.esp_tail++;
    }

    public int esp_dequeue() {
        return this.esp_data[esp_head++];
    }


}
