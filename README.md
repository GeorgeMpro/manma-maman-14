# Bloom Filter Implementation for Membership Testing

This project is a Java implementation of a Bloom Filter that allows testing false positive probabilities using configurable parameters (m and k). A Bloom Filter is a probabilistic data structure used for fast set membership testing with a trade-off between speed and accuracy. You can learn more about Bloom Filters [here](https://en.wikipedia.org/wiki/Bloom_filter).

## University Course Information

This project was part of an assignment for the course **20407: Data Structures and Introduction to Algorithms** at the Open University. The project received a grade of 99.

## Features

- Configurable parameters for the Bloom Filter (size of bit array `m` and number of hash functions `k`).
- Implementation of hash functions using MurmurHash3 from Google Guava.
- Accepts numeric or alphanumeric input values, stored and queried efficiently.
- Reports expected and actual false positive rates under different scenarios.
- Modular and extensible design for future enhancements.
- Minimal error handling and user prompts for simplicity.
- JUnit 5 tests for code correctness and easy modifications.

## Prerequisites

- Java 8 or later
- Maven

## Libraries Used

- **JUnit 5**: For writing and running unit tests.
- **Google Guava**: For MurmurHash3 implementation.
- **BitSet**: To create a dynamic bit array for the Bloom Filter.

## Setup and Running

1. Clone the repository:
   ```sh
   git clone https://github.com/GeorgeMpro/manma-maman-14.git
   ```
2. Navigate to the project directory:
   ```sh
   cd manma-maman-14
   ```
3. Compile the project:
   ```sh
   mvn clean install
   ```
4. Run the project:
   ```sh
   mvn exec:java -Dexec.mainClass="Main"
   ```

## Usage

- The program prompts for parameters `m` (size of the bit array) and `k` (number of hash functions).
- Input data is taken from files, either numeric or alphanumeric, to add elements to the Bloom Filter.
- After building the Bloom Filter, it checks membership for given elements and reports results, including false positive rates.

## Testing

- Unit tests are provided using JUnit 5 to verify functionality for hash calculations, false positives, and file operations. To run the tests:
  ```sh
  mvn test
  ```

## Example Results

```plaintext
k   |   m         |     n         |     n'        |     P           |   P'        
5   |   100000000 |     1000000   |     1000000   |     0.0000002759|   0.0485960000
Expected False Positive P=0.0000002759 or 1 in 3624186
Actual False Positive while checking n' values P'=0.0485960000 (or total 48596).
```

## Authors

- **GeorgeMpro** - [GitHub Profile](https://github.com/GeorgeMpro)
