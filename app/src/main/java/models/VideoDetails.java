package models;

public class VideoDetails {
    String title;
    String description;
    String url;
    String videoId;

    public void setVideoName(String title){
        this.title=title;
    }

    public String getVideoName(){
        return title;
    }

    public void setVideoDesc(String VideoDesc){
        this.description=VideoDesc;
    }

    public String getVideoDesc(){
        return description;
    }

    public void setURL(String URL){
        this.url=URL;
    }

    public String getURL(){
        return url;
    }

    public void setVideoId(String VideoId){
        this.videoId=VideoId;
    }
    public String getVideoId(){
        return videoId;
    }

}