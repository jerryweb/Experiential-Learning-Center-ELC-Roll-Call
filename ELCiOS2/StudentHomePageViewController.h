//
//  StudentHomePageViewController.h
//  RollCall
//
//  Created by Eric Wang on 2/29/16.
//  Copyright © 2016 Eric Wang. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface StudentHomePageViewController : UIViewController <UITableViewDelegate,UITableViewDataSource>

@property (nonatomic,strong) UISegmentedControl *attendanceFilesPicker;
@property (nonatomic,strong) UITableView *classesTable;

@end
