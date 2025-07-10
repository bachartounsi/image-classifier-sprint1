package tn.enis.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
public class ClassificationResponse {
	
	@JsonProperty("label")
    private String predictedLabel;
	
	@JsonProperty("score")
	private double confidence;

    public String getPredictedLabel() {
        return predictedLabel;
    }

    public void setPredictedLabel(String predictedLabel) {
        this.predictedLabel = predictedLabel;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

}
