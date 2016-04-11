//
//  AdminHomePageViewController.m
//  RollCall
//
//  Created by Eric Wang on 4/10/16.
//  Copyright © 2016 Eric Wang. All rights reserved.
//

#import "AdminHomePageViewController.h"
#import "AddNewClassViewController.h"
#import "ClassTableViewCell.h"

@interface AdminHomePageViewController ()

@property (nonatomic,strong) UIButton *addNewClassButton;
@property (nonatomic,strong) UITableView *classesTable;

@end

@implementation AdminHomePageViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
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
    return 5;
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

#pragma mark - button selector

- (void)addNewClass:(UIButton *)sender {
    AddNewClassViewController *avc = [[AddNewClassViewController alloc] init];
    [self.navigationController pushViewController:avc animated:NO];
}

@end
