//
//  StudentHomePageViewController.m
//  RollCall
//
//  Created by Eric Wang on 2/29/16.
//  Copyright © 2016 Eric Wang. All rights reserved.
//

#import "StudentHomePageViewController.h"
#import "StudentAttendanceHistoryViewController.h"
#import "ClassSession.h"
#import "ClassTableViewCell.h"
#import "AttendanceTableViewCell.h"

@import CoreLocation;

@interface StudentHomePageViewController () <CLLocationManagerDelegate>

@property (strong, nonatomic) CLBeaconRegion *beaconRegion;
@property (strong, nonatomic) CLBeaconRegion *classRegion;
@property (strong, nonatomic) CLLocationManager *locationManager;
@property (strong, nonatomic) NSUUID *classUUID;

@end

@implementation StudentHomePageViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.classUUID = [[NSUUID alloc] initWithUUIDString:@"07775DD0-111B-11E4-9191-0800200C9A66"];
    
    self.locationManager = [[CLLocationManager alloc] init];
    self.locationManager.delegate = self;
    [self.locationManager requestAlwaysAuthorization];
    
    NSUUID *uuid = [[NSUUID alloc] initWithUUIDString:@"07775DD0-111B-11E4-9191-0800200C9A66"];
    self.beaconRegion = [[CLBeaconRegion alloc] initWithProximityUUID:uuid
                                                                major:6400
                                                                minor:20130
                                                           identifier:@"region"];

    //[self.locationManager startMonitoringForRegion:_beaconRegion];
    [self.locationManager startRangingBeaconsInRegion:_beaconRegion];
    
    // config table view
    _classesTable = [[UITableView alloc] initWithFrame:CGRectMake(35, 45, 305, 500)];
    _classesTable.delegate = self;
    _classesTable.dataSource = self;
    _classesTable.backgroundColor = [UIColor clearColor];
    _classesTable.separatorStyle = UITableViewCellSeparatorStyleNone;
    [self.view addSubview:_classesTable];
    
    NSLog(@"view loaded");
}

#pragma mark - UITableView Data Source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
        return 4;
}

- (NSInteger)tableView:(UITableView *)tableView
 numberOfRowsInSection:(NSInteger)section {
        return 1;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
        return 101;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section
{
        return 0;
}

- (UITableViewCell *)tableView:(UITableView *)tableView
         cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    static NSString *cellIdentifier = @"StudentClass";
    
    ClassTableViewCell *cell = (ClassTableViewCell *)[tableView dequeueReusableCellWithIdentifier:cellIdentifier];
    
    if (cell == nil) {
       cell = [[ClassTableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle reuseIdentifier:cellIdentifier];
    }
    
    cell.className.text = @"CSCI401";
    cell.instructorName.text = @"Professor Jeffrey Miller";
    cell.timeLabel.text = @"MW 10-12 pm";
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(nonnull NSIndexPath *)indexPath {
    StudentAttendanceHistoryViewController *savc = [[StudentAttendanceHistoryViewController alloc] init];
    [self.navigationController pushViewController:savc animated:NO];
}

#pragma mark - iBeacon Helper Methods 

- (void)locationManager:(CLLocationManager *)manager didEnterRegion:(CLRegion *)region {
    NSLog(@"entered region");
    [self.locationManager startRangingBeaconsInRegion:self.beaconRegion];
}

- (void)locationManager:(CLLocationManager *)manager didExitRegion:(CLRegion *)region {
    [self.locationManager stopRangingBeaconsInRegion:self.beaconRegion];
    NSLog(@"exited region");
}

- (void)locationManager:(CLLocationManager *)manager
        didRangeBeacons:(NSArray *)beacons
               inRegion:(CLBeaconRegion *)region
{
    NSLog(@"beacon found!");
    CLBeacon *foundBeacon = [beacons firstObject];
    /*if([[foundBeacon.proximityUUID UUIDString] isEqualToString:[self.classUUID UUIDString]]) {
        NSLog(@"check me in!");
    } */
}

- (void)locationManager:(CLLocationManager *)manager monitoringDidFailForRegion:(CLRegion *)region withError:(NSError *)error {
    NSLog(@"Failed monitoring region: %@", error);
}

- (void)locationManager:(CLLocationManager *)manager didFailWithError:(NSError *)error {
    NSLog(@"Location manager failed: %@", error);
}

@end
