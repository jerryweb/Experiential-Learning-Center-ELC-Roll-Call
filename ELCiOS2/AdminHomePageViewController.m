//
//  AdminHomePageViewController.m
//  RollCall
//
//  Created by Eric Wang on 4/10/16.
//  Copyright © 2016 Eric Wang. All rights reserved.
//

#import "AdminHomePageViewController.h"
#import "ClassTableViewCell.h"
#import "ClassSession.h"

@interface AdminHomePageViewController ()

@property (nonatomic,strong) UIButton *addNewClassButton;
@property (nonatomic,strong) UITableView *classesTable;

@end

@implementation AdminHomePageViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    _classesArray = [[NSMutableArray alloc] init];
    
    ClassSession *c1 = [[ClassSession alloc] initWithClassName:@"CS401" withInstructorName:@"Professor Jeffrey Miller" withStartTime:@"2 pm" withEndTime:@"4 pm" withDate:@"4/13/16" withELCRoom:@"A6"];
    ClassSession *c2 = [[ClassSession alloc] initWithClassName:@"BUAD304" withInstructorName:@"Professor Judy Tolan" withStartTime:@"10 am" withEndTime:@"12 pm" withDate:@"4/10/16" withELCRoom:@"B4"];
    ClassSession *c3 = [[ClassSession alloc] initWithClassName:@"BUAD302" withInstructorName:@"Professor James Owens" withStartTime:@"4 pm" withEndTime:@"6 pm" withDate:@"3/3/16" withELCRoom:@"C2"];
    [_classesArray addObject:c1];
    [_classesArray addObject:c2];
    [_classesArray addObject:c3];
    
    _addNewClassButton = [[UIButton alloc] initWithFrame:CGRectMake(38, 45, 300, 30)];
    [_addNewClassButton setTitle:@"Add New Class" forState:UIControlStateNormal];
    [_addNewClassButton setTitleColor:[UIColor colorWithRed:255/255.0 green:255/255.0 blue:255/255.0 alpha:1.0] forState:UIControlStateNormal];
    [_addNewClassButton setBackgroundColor:[UIColor colorWithRed:80/255.0 green:227/255.0 blue:194/255.0 alpha:1.0]];
    _addNewClassButton.layer.cornerRadius = 10;
    _addNewClassButton.clipsToBounds = YES;
    [_addNewClassButton addTarget:self action:@selector(addNewClass:) forControlEvents:UIControlEventTouchUpInside];
    
    _classesTable = [[UITableView alloc] initWithFrame:CGRectMake(35, 95, 305, 500)];
    _classesTable.delegate = self;
    _classesTable.dataSource = self;
    _classesTable.backgroundColor = [UIColor clearColor];
    _classesTable.separatorStyle = UITableViewCellSeparatorStyleNone;
    
    [self.view addSubview:_addNewClassButton];
    [self.view addSubview:_classesTable];
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
    //cell.dateLabel.text = classAtRow.date;
    cell.ELCRoomLabel.text = classAtRow.ELCRoom;
    return cell;
}

#pragma mark - button selector

- (void)addNewClass:(UIButton *)sender {
    AddNewClassViewController *avc = [[AddNewClassViewController alloc] init];
    [self.navigationController pushViewController:avc animated:NO];
}

@end
