public class Main {
    public static void main(String[] args) {
        Computer computer = new Computer(
                new Processor(3.4, 8, Manufacturer.AMD, 50),
                new Memory(MemoryType.DRAM, 8, 120),
                new Storage(StorageType.HDD, 2, 700),
                new Monitor(21, MonitorType.TN, 2700),
                new Keyboard(KeyboardType.MEMBRANE, Highlight.NO, 650),
                "DNS",
                "Aqua");
        System.out.println(computer);
        System.out.println(computer.calcWeight());
        System.out.println();

        computer = computer.setMemory(MemoryType.SRAM, 12, 150)
                .setProcessor(5.2, 12, Manufacturer.INTEL, 95)
                .setStorage(StorageType.SSD, 1, 400)
                .setMonitor(27, MonitorType.IPS, 4100)
                .setKeyboard(KeyboardType.MECHANIC, Highlight.YES, 980)
                .setName("Hell-yeah")
                .setVendor("Razer");
        System.out.println(computer);
        System.out.println(computer.calcWeight());
    }
}