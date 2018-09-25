
public class Test {
    public static void main(String[] args) {
        String ip = "192.168.0.1";
        String[] ips = ip.split("\\.");

        long numberIp = 0;
        long mul = 1;
        for (int i = ips.length - 1; i >= 0; i--) {
            numberIp += Integer.parseInt(ips[i]) * mul;
            mul *= 256;
        }

        System.out.println(numberIp);
    }
}
