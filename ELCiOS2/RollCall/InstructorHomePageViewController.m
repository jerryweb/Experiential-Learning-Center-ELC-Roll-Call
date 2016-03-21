//
//  InstructorHomePageViewController.m
//  RollCall
//
//  Created by Eric Wang on 2/29/16.
//  Copyright © 2016 Eric Wang. All rights reserved.
//

#import "InstructorHomePageViewController.h"
#import "InstructorAttendanceViewController.h"
#import "ClassTableViewCell.h"

@interface InstructorHomePageViewController () <UITableViewDelegate,UITableViewDataSource>

@property (nonatomic,strong) UIButton *createClassButton;
@property (nonatomic,strong) UITableView *classesTable;

@end

@implementation InstructorHomePageViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    // config create class button
    _createClassButton = [[UIButton alloc] initWithFrame:CGRectMake(38, 45, 300, 30)];
    [_createClassButton setTitle:@"Create New Class" forState:UIControlStateNormal];
    [_createClassButton setTitleColor:[UIColor colorWithRed:255/255.0 green:255/255.0 blue:255/255.0 alpha:1.0] forState:UIControlStateNormal];
    [_createClassButton setBackgroundColor:[UIColor colorWithRed:80/255.0 green:227/255.0 blue:194/255.0 alpha:1.0]];
    _createClassButton.titleLabel.font = [UIFont fontWithName:@"HelveticaNeue" size:14];
    _createClassButton.layer.cornerRadius = 10;
    _createClassButton.clipsToBounds = YES;
    [self.view addSubview:_createClassButton];
    
    _classesTable = [[UITableView alloc] initWithFrame:CGRectMake(35, 89, 305, 300)];
    _classesTable.delegate = self;
    _classesTable.dataSource = self;
    _classesTable.backgroundColor = [UIColor clearColor];
    _classesTable.separatorStyle = UITableViewCellSeparatorStyleNone;
    [self.view addSubview:_classesTable];
}

#pragma mark - UITableView Data Source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView
 numberOfRowsInSection:(NSInteger)section {
    return 4;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    return 101;
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
    InstructorAttendanceViewController *iavc = [[InstructorAttendanceViewController alloc] init];
    [self.navigationController pushViewController:iavc animated:NO];
}

@end
