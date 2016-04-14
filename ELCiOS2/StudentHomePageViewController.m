//
//  StudentHomePageViewController.m
//  RollCall
//
//  Created by Eric Wang on 2/29/16.
//  Copyright © 2016 Eric Wang. All rights reserved.
//

#import "StudentHomePageViewController.h"
#import "ClassSession.h"
#import "ClassTableViewCell.h"

@import CoreLocation;

@interface StudentHomePageViewController () <CLLocationManagerDelegate>

@property (strong, nonatomic) CLBeaconRegion *beaconRegion;
@property (strong, nonatomic) CLBeaconRegion *classRegion;
@property (strong, nonatomic) CLLocationManager *locationManager;
@property (strong, nonatomic) NSUUID *classUUID;

@end

@implementation StudentHomePageViewController {
    int count;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    
    count = 0;
    
    _classesArray = [[NSMutableArray alloc] init];
    
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
    
    _attendanceHistory = [[UILabel alloc] initWithFrame:CGRectMake(80, 30, 240, 43)];
    _attendanceHistory.text = @"Attendance History";
    _attendanceHistory.textColor = [UIColor whiteColor];
    _attendanceHistory.font = [UIFont fontWithName:@"HelveticaNeue" size:24];
    [self.view addSubview:_attendanceHistory];
    
    // config table view
    _classesTable = [[UITableView alloc] initWithFrame:CGRectMake(35, 75, 305, 500)];
    _classesTable.delegate = self;
    _classesTable.dataSource = self;
    _classesTable.backgroundColor = [UIColor clearColor];
    _classesTable.separatorStyle = UITableViewCellSeparatorStyleNone;
    [self.view addSubview:_classesTable];
    
    NSLog(@"view loaded");
}

#pragma mark - UITableView Data Source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView
 numberOfRowsInSection:(NSInteger)section {
    return [_classesArray count];
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    return 139;
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
    
    ClassSession *classAtRow = [_classesArray objectAtIndex:indexPath.row];
    
    cell.className.text = classAtRow.className;
    cell.instructorName.text = classAtRow.instructorName;
    cell.timeLabel.text = [[classAtRow.startTime stringByAppendingString:@" - "] stringByAppendingString:classAtRow.endTime];
    cell.dateLabel.text = classAtRow.date;
    cell.ELCRoomLabel.text = classAtRow.ELCRoom;
    return cell;
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
    if([[foundBeacon.proximityUUID UUIDString] isEqualToString:[self.classUUID UUIDString]]) {
        [self checkInToClass:foundBeacon];
    }
}

- (void)locationManager:(CLLocationManager *)manager monitoringDidFailForRegion:(CLRegion *)region withError:(NSError *)error {
    NSLog(@"Failed monitoring region: %@", error);
}

- (void)locationManager:(CLLocationManager *)manager didFailWithError:(NSError *)error {
    NSLog(@"Location manager failed: %@", error);
}

- (void)checkInToClass:(CLBeacon *)beacon {
    sleep(.5);
    if (count == 0) {
        ClassSession *c1 = [[ClassSession alloc] initWithClassName:@"CS401" withInstructorName:@"Professor Jeffrey Miller" withStartTime:@"2 pm" withEndTime:@"4 pm" withDate:@"4/13/16" withELCRoom:@"A6"];
        [_classesArray addObject:c1];
        [_classesTable reloadData];
        UIAlertController *alertController = [UIAlertController alertControllerWithTitle:@"Successfully Checked In" message:@"" preferredStyle:UIAlertControllerStyleAlert];
        UIAlertAction *alertAction = [UIAlertAction actionWithTitle:@"OK" style:UIAlertActionStyleDefault handler:nil];
        [alertController addAction:alertAction];
        [self presentViewController:alertController animated:YES completion:nil];
    }
    count++;
}

@end
