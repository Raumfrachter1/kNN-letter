# kNN-letter

## Done: 
- Layer programmed
- Activation_ReLU programmed 

## In Process 
- atm u have to creat all the layers by your own. i want the possibility to creat a network with network(input, neurons_hiddenlayer1, ..., neurons_outputlayer)
- - instat of writting layer1= Layer_Dense(input, neurons_hiddenlayer1) ... for all the layers
- also i have to start the calculation for all layers by my own with layer.forward -> i want network.calculus(input) to start the calculation to the output
- i cant prove that my code works correct. 
- - i try to creat a network manual but nothing worked. I have to thing about it nüchtern and with more sleep 

## To-Do 
- atm the hole input is one batch, but i want to be able to set the batch size by my own 

- network.calculus(input, "No/Yes") should make it possible to activate or deactivate the backpropagation 

## logic atm 
1. You have some input data with a size n 
2. Then you have to create layers with Layer_Dence(number of neurons of the prev. Layer, number of neurons of the new layer)
    - (!) BE AWARE (!)  if you build a new layer u have to know the number of neurons of the prev layer or get it with prevlayer.getneurons()

3. If u are finished creating all the layers (the last one u creat is the output layer) u can start the calculation 
