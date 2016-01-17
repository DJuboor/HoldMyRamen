# Project Search && Rescue

### Mission

To reduce the cost and increase the functionality in modern search and rescue applications in a post-hurricane environment by utilizing automated quadcopter drone(s) and image recognition algorithms. Our hardware is designed to reduce the overall flight time of S&R helicopters by drone-scouting a predetermined area, locating survivors, and transmitting their coordinants to the S&R station. Upon further testing, our software is designed to be able to support:

  - Thermal recognition (visual feed FTIR)
  - Machine learning principles (detecting false positives)
  - Gaussian Coordinates (stabilization and location) 

For the scope of this hackathon, our hardware has an automated flightpath in which it collects aerial images over certain "stop points." As images are collected they are analyzed using modal factor RGB normalization and are "scanned" for objects in a certain color range (a rubber duck). Once found, the location is sent to the user in the form of Cartesian coordinates. Theoretically, once the location is sent to the user, the used is then able to go "rescue" the duck without having to manually search via a search and rescue helicopter. This significantly reduces the cost of S&R endeavors and greatly increases the efficiency of S&R teams as a whole.
