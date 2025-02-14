# -*- coding: utf-8 -*-
"""pa1.ipynb

Automatically generated by Colab.

Original file is located at
    https://colab.research.google.com/drive/1fH5ShM6H-subY7iInEktjSGB0Zx9hsTh

# **Set up the environment**

# **Import Libraries**
"""

import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import nltk
from sklearn.model_selection import train_test_split
from sklearn.model_selection import GridSearchCV
from sklearn.preprocessing import StandardScaler
from sklearn.linear_model import LogisticRegression
from sklearn.naive_bayes import MultinomialNB
from sklearn.svm import SVC
from sklearn.linear_model import Perceptron
from sklearn.metrics import accuracy_score, confusion_matrix, classification_report
from nltk.corpus import stopwords
from sklearn.feature_extraction.text import TfidfVectorizer
from google.colab import drive
from sklearn.model_selection import cross_val_score
drive.mount('/content/drive')

"""# **Load and Prepare Data**"""

#Reading the files
with open('/facts.txt', 'r') as f:
    facts = f.readlines()

with open('fakes.txt', 'r') as f:
    fakes = f.readlines()

#Combine the two lists and create corresponding labels
data = facts + fakes
labels = [1] * len(facts) + [0] * len(fakes)  # 1 for real, 0 for fake

"""# **Preprocess the Data**"""

# Download stopwords
nltk.download('stopwords')
stop_words = set(stopwords.words('english'))

# 3 Preprocessing decisions: Tokenization, Lowercasing & Stop word removal

# Function to preprocess text
def preprocessText(text):
    # Tokenization and lowercasing
    tokens = text.lower().split()

    # Remove stop words
    tokens = [word for word in tokens if word not in stop_words]

    return ' '.join(tokens)

# Apply the preprocessing to all the data
preprocessed_data = [preprocessText(fact) for fact in data]

"""# **Feature Extraction**"""

# Initialize TF-IDF vectorizer
vectorizer = TfidfVectorizer(max_features=1000)  # You can adjust max_features as needed

# Fit and transform the preprocessed data
X = vectorizer.fit_transform(preprocessed_data)

"""# **Splitting Testing and Training Data**"""

# Split data into training and testing sets
X_train, X_test, y_train, y_test = train_test_split(X, labels, test_size=0.3, random_state=42)

"""# **Define Hyperparameter grids for each model**"""

# Define hyperparameter grids for each model
param_grid_multinomial_nb = {'alpha': [0.1, 0.5, 1.0, 5.0, 10.0]} # Smoothing parameter

param_grid_svm = {'C': [0.01, 0.1, 1, 10, 100]}  # Regularization parameter
param_grid_perceptron = {'penalty': [None, 'l2', 'l1', 'elasticnet'],  # Penalty term
    'alpha': [0.0001, 0.001, 0.01, 0.1]  # Learning rate regularization
}

"""# **Grid Search for our 3 Classifiers**"""

# Grid search for Multinomial Naive Bayes
grid_multinomial_nb = GridSearchCV(MultinomialNB(), param_grid_multinomial_nb, cv=5, scoring='accuracy')
grid_multinomial_nb.fit(X_train, y_train)

# Grid search for SVM
grid_svm = GridSearchCV(SVC(kernel='linear'), param_grid_svm, cv=5, scoring='accuracy')
grid_svm.fit(X_train, y_train)

# Grid search for Perceptron
grid_perceptron = GridSearchCV(Perceptron(), param_grid_perceptron, cv=5, scoring='accuracy')
grid_perceptron.fit(X_train, y_train)

"""# **Linear Classifier 1: Naive Bayes Classifier**

### **Training the Linear Classifier**
"""

# Initialize the Naive Bayes classifier
nb_model = MultinomialNB()

# Train the model on the training set
nb_model.fit(X_train, y_train)

# Make predictions on the test set
y_pred = nb_model.predict(X_test)

"""### **Evaluating & Optimizing Classifier**"""

# Evaluate the model
accuracy = accuracy_score(y_test, y_pred)
conf_matrix = confusion_matrix(y_test, y_pred)
class_report = classification_report(y_test, y_pred)

print(f"Accuracy: {accuracy:.2f}")
print("Confusion Matrix:")
print(conf_matrix)
print("Classification Report:")
print(class_report)

# Cross-validation accuracy
cv_scores = cross_val_score(nb_model, X, labels, cv=5)
print(f"Cross-Validation Accuracy: {np.mean(cv_scores):.2f}")

"""# **Linear Classifier 2: Linear Support Vector Classifier (SVM)**

### **Training the Linear Classifier**
"""

# Initialize the LinearSVC classifier
svmm_linear = SVC(kernel='linear')

# Train the model on the training set
svm_linear.fit(X_train, y_train)

# Make predictions on the test set
y_pred = svm_linear.predict(X_test)

"""### **Evaluating & Optimizing Classifier**"""

# Evaluate the model
accuracy = accuracy_score(y_test, y_pred)
conf_matrix = confusion_matrix(y_test, y_pred)
class_report = classification_report(y_test, y_pred)

print(f"Accuracy: {accuracy:.2f}")
print("Confusion Matrix:")
print(conf_matrix)
print("Classification Report:")
print(class_report)

# Cross-validation accuracy
cv_scores = cross_val_score(svm_linear, X, labels, cv=5)
print(f"Cross-Validation Accuracy: {np.mean(cv_scores):.2f}")

"""# **Linear Classifier 3: Perceptron**

### **Training the Linear Classifier**
"""

# Initialize the Perceptron classifier
perceptron_model = Perceptron(random_state=42)

# Train the model on the training set
perceptron_model.fit(X_train, y_train)

# Make predictions on the test set
y_pred = perceptron_model.predict(X_test)

"""### **Evaluating & Optimizing Classifier**"""

# Evaluate the model
accuracy = accuracy_score(y_test, y_pred)
conf_matrix = confusion_matrix(y_test, y_pred)
class_report = classification_report(y_test, y_pred)

print(f"Accuracy: {accuracy:.2f}")
print("Confusion Matrix:")
print(conf_matrix)
print("Classification Report:")
print(class_report)