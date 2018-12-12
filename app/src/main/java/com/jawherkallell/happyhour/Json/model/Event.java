package com.jawherkallell.happyhour.Json.model;

public class Event {

        private String adresse;

        private String rue;

        private String designation;

        private String img;

        private String code_p;

        private String ville;

        private String date;

        private String id;

        private String geolocation;

        private String description;

        private String pays;

        private String longitude;

        private String latitude;

        public String getAdresse ()
        {
            return adresse;
        }

        public void setAdresse (String adresse)
        {
            this.adresse = adresse;
        }

        public String getRue ()
        {
            return rue;
        }

        public void setRue (String rue)
        {
            this.rue = rue;
        }

        public String getDesignation ()
        {
            return designation;
        }

        public void setDesignation (String designation)
        {
            this.designation = designation;
        }

        public String getImg ()
        {
            return img;
        }

        public void setImg (String img)
        {
            this.img = img;
        }

        public String getCode_p ()
        {
            return code_p;
        }

        public void setCode_p (String code_p)
        {
            this.code_p = code_p;
        }

        public String getVille ()
        {
            return ville;
        }

        public void setVille (String ville)
        {
            this.ville = ville;
        }

        public String getDate ()
        {
            return date;
        }

        public void setDate (String date)
        {
            this.date = date;
        }

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        public String getGeolocation ()
        {
            return geolocation;
        }

        public void setGeolocation (String geolocation)
        {
            this.geolocation = geolocation;
        }

        public String getDescription ()
        {
            return description;
        }

        public void setDescription (String description)
        {
            this.description = description;
        }

        public String getPays ()
        {
            return pays;
        }

        public void setPays (String pays)
        {
            this.pays = pays;
        }

        public String getLongitude ()
        {
            return longitude;
        }

        public void setLongitude (String longitude)
        {
            this.longitude = longitude;
        }

        public String getLatitude ()
        {
            return latitude;
        }

        public void setLatitude (String latitude)
        {
            this.latitude = latitude;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [adresse = "+adresse+", rue = "+rue+", designation = "+designation+", img = "+img+", code_p = "+code_p+", ville = "+ville+", date = "+date+", id = "+id+", geolocation = "+geolocation+", description = "+description+", pays = "+pays+", longitude = "+longitude+", latitude = "+latitude+"]";
        }
    }


