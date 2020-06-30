package com.atlassian.tutorial.ao.todo;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public enum TaskTypeEnum {
        EDITCONTENT ("EditContent"),
        PUBLISHBANNER ("PublishBanner"),
        UPLOADFILES ("UploadFiles"),
        PUBLICATIONFOROFFICES ("PublicationForOffices"),
        TRANSLATEANDPUBLICATION ("TranslateAndPublication"),
        PUBLISHICON ("PublishIcon"),
        PLACEMENTOFMATERIALS ("PlacementOfMaterials"),
        NEWPAGE ("NewPage");

        private String title;

        TaskTypeEnum(String title) {
            this.title=title;
        }



        public String getName() {
            return this.title;
        }

        public List<TaskTypeEnum> getAll() {
            return newArrayList(TaskTypeEnum.values());
        }

}
