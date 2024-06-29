package com.abc.api_web_prueba.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuerylyResponse {
    @JsonProperty("metadata")
    private Metadata metadata;

    @JsonProperty("faceted")
    private Faceted faceted;

    @JsonProperty("items")
    private List<Item> items;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Metadata {
        @JsonProperty("query")
        private String query;

        @JsonProperty("total")
        private int total;

        @JsonProperty("endindex")
        private int endIndex;

        @JsonProperty("correction")
        private String correction;

        @JsonProperty("suggest")
        private String suggest;

        @JsonProperty("filters")
        private List<String> filters;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Faceted {
        @JsonProperty("section")
        private List<Section> section;

        @JsonProperty("pubDate")
        private List<PubDate> pubDate;

        @JsonProperty("tag")
        private List<Tag> tag;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Section {
        @JsonProperty("key")
        private String key;

        @JsonProperty("value")
        private int value;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PubDate {
        @JsonProperty("key")
        private String key;

        @JsonProperty("value")
        private int value;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Tag {
        @JsonProperty("key")
        private String key;

        @JsonProperty("value")
        private int value;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        @JsonProperty("_id")
        private int _id;

        @JsonProperty("index")
        private int index;

        @JsonProperty("title")
        private String title;

        @JsonProperty("link")
        private String link;

        @JsonProperty("description")
        private String description;

        @JsonProperty("pubdate")
        private String pubdate;

        @JsonProperty("pubdateunix")
        private String pubdateunix;

        @JsonProperty("image")
        private String image;

        @JsonProperty("_type")
        private int _type;

        @JsonProperty("feedname")
        private String feedname;

        @JsonProperty("creator")
        private String creator;

        @JsonProperty("imageresizer")
        private String imageresizer;

        @JsonProperty("promo_image")
        private String promo_image;
    }
}

