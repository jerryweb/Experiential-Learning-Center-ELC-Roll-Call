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
@property (strong, nonatomic) CLLocationManager *locationManager;

@end

@implementation StudentHomePageViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.locationManager = [[CLLocationManager alloc] init];
    self.locationManager.delegate = self;
    
    NSUUID *uuid = [[NSUUID alloc] initWithUUIDString:@"a500248c-abc2-4206-9bd7-034f4fc9ed10"];
    self.beaconRegion = [[CLBeaconRegion alloc] initWithProximityUUID:uuid identifier:@"com.Eric-Wang.RollCall"];
    [self.locationManager startMonitoringForRegion:_beaconRegion];
    
    // config table view
    _classesTable = [[UITableView alloc] initWithFrame:CGRectMake(35, 45, 305, 500)];
    _classesTable.delegate = self;
    _classesTable.dataSource = self;
    _classesTable.backgroundColor = [UIColor clearColor];
    _classesTable.separatorStyle = UITableViewCellSeparatorStyleNone;
    _classesTable.hidden = YES;
    [self.view addSubview:_classesTable];
    
    [self performSelector:@selector(showTable) withObject:self afterDelay:5.0];
    
    NSLog(@"view loaded");
}

#pragma mark - UITableView Data Source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
        return 1;
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
    [self.locationManager startRangingBeaconsInRegion:self.beaconRegion];
}

- (void)locationManager:(CLLocationManager *)manager didExitRegion:(CLRegion *)region {
    [self.locationManager stopRangingBeaconsInRegion:self.beaconRegion];
    NSLog(@"exited region");
}

- (void)locationManager:(CLLocationManager *)manager
        didRangeBeacons:(nonnull NSArray<CLBeacon *> *)beacons
               inRegion:(nonnull CLBeaconRegion *)region {
    NSLog(@"beacon found!");
    CLBeacon *foundBeacon = [beacons firstObject];
}

#pragma mark - Table Selector

- (void)showTable {
    self.classesTable.hidden = NO;
}

@end
