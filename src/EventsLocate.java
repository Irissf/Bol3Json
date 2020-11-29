public class EventsLocate {
    private String titleEvent;
    private String UrlEvent;
    private String city_name;

    private String adress;
    private String timeStart;
    private String description;
    private String countryName;

    // SETTERS
    // GETTERS=====================================================================

    public String getCity_name() {
        return city_name;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTitleEvent() {
        return titleEvent;
    }

    public void setTitleEvent(String titleEvent) {
        this.titleEvent = titleEvent;
    }

    public String getUrlEvent() {
        return UrlEvent;
    }

    public void setUrlEvent(String urlEvent) {
        this.UrlEvent = urlEvent;
    }

    public void setCity_numer(String city_numer) {
        this.city_name = city_numer;
    }

    // CONSTRUCTOR=====================================================================

    public EventsLocate(String titleEvent, String urlEvent, String city_name, String adress, String timeStart,
            String description, String countryName) {
        this.titleEvent = titleEvent;
        UrlEvent = urlEvent;
        this.city_name = city_name;
        this.setAdress(adress);
        this.setTimeStart(timeStart);
        this.setDescription(description);
        this.setCountryName(countryName);
    }
}
