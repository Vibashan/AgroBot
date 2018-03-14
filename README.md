# auto_navi_expo
                                                                                                              
In this project, we build an autonomous terrestrial rover which can take care of indoor farming from plantation till before
harvesting under ROS platform. We did 2D SLAM(gmapping) with Kinect Laser scan and Odom frame obtained from wheel encoder
and gyroscope. We used teleop key to explore the indoor arena and used move base to navigate desired points. 

## Video Link :
https://www.youtube.com/watch?v=_kQo_sd1_AM

## Hardware Requirements :
  – Two geared DC motors with integrated encoder 
  – Xbox kinect 360 
  – Lenovo Zuk2/ Android 
  – Arduino uno 320p                                                                                                          
  – 6800mAh 3S LiPo battery (and balance charger)
  – Wood planks for chassis base
  – L shaped metal brackets                                                                                                
  – Nuts and Bolts                                                                                                          
  – wires and cables                                                                                        
 
## Software Requirements :                                                                                                  
  – Ubuntu 16.04 and ROS kinetic                                                                                        
  – OpenCV                                                                                                                  
  – Tensorflow                                                                                                              
  – Android Studio
## Procedure:

1. First clone my github repo using the command: $ git clone address/to/my/repo
2. Install ros packages for navigation, freenect, and gmapping.
3. Change the wheel diameter, track width, etc. in robot_specs.h
4. Edit in robot_config.launch the static_transform_publisher args for tranformation of Kinect frame to Base link.
5. If you aren’t using an android phone as an imu like I did, comment out the imu_node lines in robot_config.launch
6. If you haven’t created a urdf model for your robot, comment out the urdf include lines in driver.launch, slam.launch,
   move_base.launch, and laser_scan.launch
7. Try driving around the robot : $ roslaunch auto_navi_expo driver.launch
8. For trying the laser scanner, SLAM gmapping, and navigation, roslaunch laser_scan.launch, slam.launch, and
move_base.launch respectively.
9. You will need to play around with the parameter settings in the launch files, such as the linear and angular scale constants in robot_config.launch, the params in amcl_diff.launch, the yaml files, etc.

Future Work:                                                                                                    
  Navigate to Multiway_points.
