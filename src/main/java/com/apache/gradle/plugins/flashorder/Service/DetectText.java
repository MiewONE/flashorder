package com.apache.gradle.plugins.flashorder.Service;


import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.ImageSource;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DetectText {

    public static void detectTextGcs() throws IOException {
        // TODO(developer): Replace these variables before running the sample.
        String filePath = "https://www.dogdrip.net/dvs/d/20/09/08/bb8f6e31c8b7192c05c4efea37670adf.jpeg";
        detectTextGcs(filePath);
    }

    // Detects text in the specified remote image on Google Cloud Storage.
    public static ArrayList<String> detectTextGcs(String gcsPath) throws IOException {
        List<AnnotateImageRequest> requests = new ArrayList<>();
        ArrayList<String> arrayList = new ArrayList<>();
        ImageSource imgSource = ImageSource.newBuilder().setImageUri(gcsPath).build();
        Image img = Image.newBuilder().setSource(imgSource).build();
        Feature feat = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();
        AnnotateImageRequest request =
                AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
        requests.add(request);

        // Initialize client that will be used to send requests. This client only needs to be created
        // once, and can be reused for multiple requests. After completing all of your requests, call
        // the "close" method on the client to safely clean up any remaining background resources.
        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();

            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    arrayList.add(res.getError().getMessage());
//                    System.out.format("Error: %s%n", res.getError().getMessage());
//                    return arrayList;
                }

                // For full list of available annotations, see http://g.co/cloud/vision/docs
                for (EntityAnnotation annotation : res.getTextAnnotationsList()) {
                    arrayList.add(annotation.getDescription());
//                    return arrayList;
//                    System.out.format("Text: %s%n", annotation.getDescription());
//                    System.out.format("Position : %s%n", annotation.getBoundingPoly());
                }
            }
            return arrayList;
        }
    }
}
