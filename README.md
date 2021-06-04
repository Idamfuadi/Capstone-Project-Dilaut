
# Dilaut
A catalog fish prices application which contains current month price and price fishes prediction for next month. The core of our idea is to help fishermen to sell their catch directly to the community, and maintain price stability among the community. Hopefully, it would help by providing transparency of fish prices, so that fishermen are not trapped by the prices given by middlemen, especially in Gresik, Jawa Timur.

## Our Team
#### Team ID :
B21-CAP0333

#### Team Members :
1. C1721789 - Joko Handoko
2. C1721805 - Samuel Partogi Pakpahan
3. A0040257 - Latief Ni'Am Asadulloh
4. A0040260 - Mohamad Idam Fuadi 
5. M1131442 - Ni Luh Putu Anita Dewi 
6. M1141449 - Maria Zanissa Hutagalung

# Tech Stack
## Machine Learning
- Created a ML Model to predict prices using LSTM
Using keras as the model and using Long Short Term Memory as the training algorithm. LSTMs are designed to avoid the long-term dependency problem. Remembering information for long periods of time is practically their default behavior. The model contains 2 LSTM layers with 128 units and 25 units, then 3 layer for the hidden layer with 128 units for first layer, 128 units for the second layer, and 64 units for the last.The output layer contains one unit.

## Android
- Create simple UI for app
- Using Retrofit Method to integration API from Cloud
- Use MVVM architecture to make code better to change
- Github Collaboration (MD,ML,Cloud) for make project


## Cloud Computing
- In GCP create a Project with an unique global name and activate the billing to start using services from GCP
- Create Bucket in Cloud Storage to store all images that will be later for Cloud Function
- Create API in Cloud Function that generate a JSON and sent to Android
