package trinm.utils;

import java.io.File;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import trinm.dtos.TourDTO;

/**
 *
 * @author TNM
 */
public class Utilities implements Serializable {

    public static int manageCart(List<TourDTO> list, TourDTO item) throws Exception {
        boolean itemInCart = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(item.getId())) {
                int slotBooked = list.get(i).getQuota() + item.getQuota();
                list.get(i).setQuota(slotBooked);
                itemInCart = true;
            }
        }
        if (!itemInCart) {
            list.add(item);
        }
        return list.size();
    }

    public static double getTotalCartPrice(List<TourDTO> list, int discountValue) throws Exception {
        double result = 0;
        double discount = (100 - discountValue);
        discount /= 100;
        for (int i = 0; i < list.size(); i++) {
            double totalPrice = list.get(i).getQuota() * list.get(i).getPrice() * discount;
            result += totalPrice;
        }
        return result;
    }

    public static void manageTemporaryTourList(List<TourDTO> list, List<TourDTO> cart) throws Exception {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < cart.size(); j++) {
                if (list.get(i).getId().equals(cart.get(j).getId())) {
                    String tourName = list.get(i).getTourName() + " (In-Cart)";
                    int slotAvailable = list.get(i).getSlotAvailable() - cart.get(j).getQuota();
                    list.get(i).setTourName(tourName);
                    if (slotAvailable >= 0) {
                        list.get(i).setSlotAvailable(slotAvailable);
                    } else {
                        list.get(i).setSlotAvailable(0);
                    }
                }
            }
        }
    }

    public static int[] manageNumberLimit(String rangeString) throws Exception {
        int index = rangeString.indexOf(" ");
        int limitLow = Integer.parseInt(rangeString.substring(0, index));
        index = rangeString.lastIndexOf(" ");
        int limitHigh = Integer.parseInt(rangeString.substring(index + 1));
        int[] priceRange = {limitLow, limitHigh};
        return priceRange;
    }

    public static boolean compareDate(String startDate, String endDate) throws Exception {
        boolean dateSwitched = false;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateStart = format.parse(startDate);
        Date dateEnd = format.parse(endDate);
        long difference = dateEnd.getTime() - dateStart.getTime();
        if (difference <= 0) {
            dateSwitched = true;
        }
        return dateSwitched;
    }

    public static String[] manageDateLimit(String startDate, String endDate) throws Exception {
        if (compareDate(startDate, endDate)) {
            String temp = startDate;
            startDate = endDate;
            endDate = temp;
        }
        String[] dateRange = {startDate, endDate};
        return dateRange;
    }

    public static List<String> getImageList() {
        List<String> fileName = new ArrayList<>();
        String defaultPath = Utilities.class.getProtectionDomain().getCodeSource().getLocation().toString();
        String folderPath = defaultPath.substring(5, defaultPath.length() - 26);
        File directory = new File(folderPath + "web/img/tourIMG/");
        if (directory.exists()) {
            File[] fileList = directory.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                String name = fileList[i].getName();
                String extension = name.substring(name.length() - 3);
                if ((extension.equals("jpg") || extension.equals("png")) && !name.equals("TBA.jpg")) {
                    fileName.add(name);
                }
            }
        }
        return fileName;
    }
}
