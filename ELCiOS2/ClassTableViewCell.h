//
//  ClassTableViewCell.h
//  RollCall
//
//  Created by Eric Wang on 3/2/16.
//  Copyright © 2016 Eric Wang. All rights reserved.
//

#import <UIKit/UIKit.h>

static NSString * const kCellIDStudent = @"StudentClass";

@interface ClassTableViewCell : UITableViewCell

@property (nonatomic,strong) UILabel *className;
@property (nonatomic,strong) UILabel *instructorName;
@property (nonatomic,strong) UILabel *timeLabel;
@property (nonatomic,strong) UILabel *dateLabel;
@property (nonatomic,strong) UILabel *ELCRoomLabel;
@property (nonatomic,strong) UIImageView *attendanceStatus;

@end
