//
//  StudentHomePageViewController.h
//  RollCall
//
//  Created by Eric Wang on 2/29/16.
//  Copyright © 2016 Eric Wang. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface StudentHomePageViewController : UIViewController <UITableViewDelegate,UITableViewDataSource>

@property (nonatomic,strong) UILabel *attendanceHistory;
@property (nonatomic,strong) UITableView *classesTable;
@property (nonatomic,strong) NSMutableArray *classesArray;

@end
