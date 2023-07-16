package com.cheatbreaker.client.util.dash;

import com.cheatbreaker.bridge.util.ResourceLocationBridge;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

public class Station {
    @Getter
    private final String streamURL;
    @Getter
    private final String currentSongURL;
    @Getter
    private final String genre;
    @Getter
    private final String logoURL;
    @Getter
    private final String name;
    @Getter
    @Setter
    private boolean favourite;
    @Getter
    @Setter
    private static LocalDateTime startTime;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String artist;
    @Getter
    @Setter
    private String album;
    @Getter
    @Setter
    private String coverURL = "";
    @Getter
    @Setter
    private int duration;
    @Getter
    public ResourceLocationBridge currentResource;
    @Getter
    public ResourceLocationBridge previousResource;
    public boolean play;

    public Station(String name, String logoURL, String genre, String currentSongURL, String streamURL) {
        this.name = name;
        this.logoURL = logoURL;
        this.genre = genre;
        this.currentSongURL = currentSongURL;
        this.streamURL = streamURL;
    }

    public void endStream() {
        DashUtil.end(DashUtil.dashHelpers(this.streamURL));
    }

    public void getData() {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.getCurrentSongURL());
            NodeList nodeList = document.getElementsByTagName("playlist");
            for (int i = 0; i < nodeList.getLength(); ++i) {
                Node node = nodeList.item(i);
                Element element = (Element)node;
                this.setTitle(this.getElement(element, "title"));
                this.setArtist(this.getElement(element, "artist"));
                this.setAlbum(this.getElement(element, "album"));
                this.setCoverURL(this.getElement(element, "cover"));
                this.setDuration(Integer.parseInt(this.getElement(element, "duration")));
                String string = this.getElement(element, "programStartTS");
                String string2 = "dd MMM yy hh:mm:ss";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(string2);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                if (this.currentResource != null && !("client/songs/" + this.getTitleForResource())
                        .equals(this.currentResource.bridge$getResourcePath())) {
                    this.previousResource = this.currentResource;
                    this.currentResource = null;
                }
                try {
                    Date date = simpleDateFormat.parse(string);
                    setStartTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
                    continue;
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        if (this.play) {
            this.play = false;
            this.endStream();
        }
    }

    private String getElement(Element element, String string) {
        try {
            NodeList nodeList = element.getElementsByTagName(string);
            Element element2 = (Element)nodeList.item(0);
            NodeList nodeList2 = element2.getChildNodes();
            return nodeList2.item(0).getNodeValue().trim();
        }
        catch (Exception exception) {
            return "";
        }
    }

    public String getTitleForResource() {
        return this.getTitle().toLowerCase().replaceAll("[^a-z0-9/._-]", "_");
    }

    public boolean isPlay() {
        return this.play;
    }

    public String toString() {
        return "Station(" +
                "streamUrl=" + this.getStreamURL() +
                ", currentSongUrl=" + this.getCurrentSongURL() +
                ", genre=" + this.getGenre() +
                ", logoUrl=" + this.getLogoURL() +
                ", name=" + this.getName() +
                ", favorite=" + this.isFavourite() +
                ", startTime=" + getStartTime() +
                ", title=" + this.getTitle() +
                ", artist=" + this.getArtist() +
                ", album=" + this.getAlbum() +
                ", coverUrl=" + this.getCoverURL() +
                ", duration=" + this.getDuration() +
                ", RESOURCE_CURRENT=" + this.getCurrentResource() +
                ", RESOURCE_PREVIOUS=" + this.getPreviousResource() +
                ", play=" + this.isPlay() + ")";
    }
}
 