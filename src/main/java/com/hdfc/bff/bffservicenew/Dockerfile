# -----------------------------
# Dockerfile for BFF Service
# -----------------------------

# Use lightweight Java 17 image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy Maven build output
COPY target/bff-service-*.jar app.jar

# Expose port (Railway injects PORT automatically)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
