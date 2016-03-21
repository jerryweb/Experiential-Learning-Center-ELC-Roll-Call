//
//  InstructorAttendanceViewController.m
//  RollCall
//
//  Created by Eric Wang on 2/29/16.
//  Copyright © 2016 Eric Wang. All rights reserved.
//

#import "InstructorAttendanceViewController.h"
#import "AttendanceTableViewCell.h"

@interface InstructorAttendanceViewController ()

@property (nonatomic,strong) UILabel *classLabel;
@property (nonatomic,strong) UITableView *attendanceTable;

@end

@implementation InstructorAttendanceViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    _classLabel = [[UILabel alloc] initWithFrame:CGRectMake(38, 45, 300, 40)];
    _classLabel.textColor = [UIColor whiteColor];
    _classLabel.font = [UIFont fontWithName:@"HelveticaNeue" size:13];
    _classLabel.backgroundColor = [UIColor colorWithRed:80/255.0 green:227/255.0 blue:194/255.0 alpha:.6];
    _classLabel.textAlignment = NSTextAlignmentCenter;
    _classLabel.text = @"CSCI 401";
    [self.view addSubview:_classLabel];
    
    _attendanceTable = [[UITableView alloc] initWithFrame:CGRectMake(38, 87, 300, 400)];
    _attendanceTable.delegate = self;
    _attendanceTable.dataSource = self;
    _attendanceTable.backgroundColor = [UIColor clearColor];
    _attendanceTable.separatorStyle = UITableViewCellSeparatorStyleNone;
    [self.view addSubview:_attendanceTable];
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView
 numberOfRowsInSection:(NSInteger)section {
    return 4;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    return 32;
}

- (UITableViewCell *)tableView:(UITableView *)tableView
         cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    static NSString *cellIdentifier = @"StudentClass";
    
    AttendanceTableViewCell *cell = (AttendanceTableViewCell *)[tableView dequeueReusableCellWithIdentifier:cellIdentifier];
    
    if (cell == nil) {
        cell = [[AttendanceTableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle reuseIdentifier:cellIdentifier];
    }
    
    cell.nameDateLabel.text = @"Kerry Chen";
    return cell;
}

@end
