package location;

public class LocationImpl implements Location{
    private String longitude;
    private String latitude;
    private String city;
    private String state;


    public LocationImpl(String longitude, String latitude, String city, String state){
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
        this.state = state;
    }

    @Override
    public String getLongitude() {
        return longitude;
    }

    @Override
    public String getLatitude() {
        return latitude;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getState() {
        return state;
    }
}
