//
//  StudentHomePageViewController.m
//  RollCall
//
//  Created by Eric Wang on 2/29/16.
//  Copyright © 2016 Eric Wang. All rights reserved.
//

#import "StudentHomePageViewController.h"
#import "StudentAttendanceHistoryViewController.h"
#import "ClassTableViewCell.h"
#import "AttendanceTableViewCell.h"

@interface StudentHomePageViewController () 

@end

@implementation StudentHomePageViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    // config segmented control
    [[UISegmentedControl appearance] setTintColor:[UIColor colorWithRed:80/255.0 green:227/255.0 blue:194/255.0 alpha:1.0]];
    NSArray *optionsArray = @[@"Attendance",@"Uploaded Files"];
    _attendanceFilesPicker = [[UISegmentedControl alloc] initWithItems:optionsArray];
    _attendanceFilesPicker.frame = CGRectMake(38, 45, 300, 26);
    _attendanceFilesPicker.segmentedControlStyle = UISegmentedControlStylePlain;
    [_attendanceFilesPicker addTarget:self action:@selector(toggleOptions:) forControlEvents:UIControlEventValueChanged];
    _attendanceFilesPicker.selectedSegmentIndex = 0;
    [self.view addSubview:_attendanceFilesPicker];
    
    // config table view
    _classesTable = [[UITableView alloc] initWithFrame:CGRectMake(35, 90, 305, 500)];
    _classesTable.delegate = self;
    _classesTable.dataSource = self;
    _classesTable.backgroundColor = [UIColor clearColor];
    _classesTable.separatorStyle = UITableViewCellSeparatorStyleNone;
    [self.view addSubview:_classesTable];
    
    _filesTable = [[UITableView alloc] initWithFrame:CGRectMake(35, 90, 305, 500)];
    _filesTable.delegate = self;
    _filesTable.dataSource = self;
    _filesTable.backgroundColor = [UIColor clearColor];
    _filesTable.separatorStyle = UITableViewCellSeparatorStyleNone;
    _filesTable.hidden = YES;
    [self.view addSubview:_filesTable];
}

#pragma mark - UITableView Data Source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    if (tableView == _filesTable) {
        return 3;
    }
        return 1;
}

- (NSInteger)tableView:(UITableView *)tableView
 numberOfRowsInSection:(NSInteger)section {
    if (tableView == _filesTable) {
        return 2;
    }
        return 4;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (tableView == _filesTable) {
        return 44;
    }
        return 101;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section
{
    if (tableView == _filesTable) {
        return 54;
    }
        return 0;
}

-(UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section
{
    if (tableView == _filesTable) {
    // initial view
    UIView *view = [[UIView alloc] initWithFrame:CGRectMake(0, 0, 300, 54)];
    [view setBackgroundColor:[UIColor clearColor]];
    
    // cell area
    UILabel *backgroundView = [[UILabel alloc] initWithFrame:CGRectMake(0, 0, tableView.frame.size.width, 40)];
    backgroundView.backgroundColor = [UIColor colorWithRed:80/255.0 green:227/255.0 blue:194/255.0 alpha:.6];
    backgroundView.text = @"CSCI 401";
    backgroundView.textColor = [UIColor whiteColor];
    backgroundView.font = [UIFont fontWithName:@"HelveticaNeue" size:13];
    backgroundView.textAlignment = NSTextAlignmentCenter;
    [view addSubview:backgroundView];
    return view;
    }
    return nil;
}

- (UITableViewCell *)tableView:(UITableView *)tableView
         cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    static NSString *cellIdentifier = @"StudentClass";
    
    if (tableView == _filesTable) {
        AttendanceTableViewCell *cell = (AttendanceTableViewCell *)[tableView dequeueReusableCellWithIdentifier:cellIdentifier];
        
        if (cell == nil) {
            cell = [[AttendanceTableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:cellIdentifier];
        }
        
        cell.nameDateLabel.text = @"Eric Wang Presentation";
        return cell;
    }
    else {
    ClassTableViewCell *cell = (ClassTableViewCell *)[tableView dequeueReusableCellWithIdentifier:cellIdentifier];
    
    if (cell == nil) {
       cell = [[ClassTableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle reuseIdentifier:cellIdentifier];
    }
    
    cell.className.text = @"CSCI401";
    cell.instructorName.text = @"Professor Jeffrey Miller";
    cell.timeLabel.text = @"MW 10-12 pm";
    return cell;
    }
    return nil;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(nonnull NSIndexPath *)indexPath {
    StudentAttendanceHistoryViewController *savc = [[StudentAttendanceHistoryViewController alloc] init];
    [self.navigationController pushViewController:savc animated:NO];
}

- (void)toggleOptions:(UISegmentedControl *)segment {
    if(segment.selectedSegmentIndex == 0) {
        _classesTable.hidden = NO;
        _filesTable.hidden = YES;
    } else if(segment.selectedSegmentIndex == 1) {
        _classesTable.hidden = YES;
        _filesTable.hidden = NO;
    }
}

@end
