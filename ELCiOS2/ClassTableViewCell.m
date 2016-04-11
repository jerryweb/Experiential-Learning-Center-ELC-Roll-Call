//
//  ClassTableViewCell.m
//  RollCall
//
//  Created by Eric Wang on 3/2/16.
//  Copyright © 2016 Eric Wang. All rights reserved.
//

#import "ClassTableViewCell.h"
#import <QuartzCore/QuartzCore.h>

@implementation ClassTableViewCell

- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier {
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    
    if(self) {
        
    self.backgroundColor = [UIColor clearColor];
        
    UIView *backgroundView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, 305, 87)];
    backgroundView.backgroundColor = [UIColor colorWithRed:151.0 green:151.0 blue:151.0 alpha:.3];
    backgroundView.layer.cornerRadius = 5;
    backgroundView.layer.masksToBounds = YES;
    [self addSubview:backgroundView];
        
    // config ui
    _className = [[UILabel alloc] initWithFrame:CGRectMake(12, 12, 104, 26)];
    _className.textColor = [UIColor whiteColor];
    _className.font = [UIFont fontWithName:@"HelveticaNeue" size:22];
    [self addSubview:_className];
        
    _instructorName = [[UILabel alloc] initWithFrame:CGRectMake(12, 42, 130, 15)];
    _instructorName.textColor = [UIColor whiteColor];
    _instructorName.font = [UIFont fontWithName:@"HelveticaNeue" size:12];
    [self addSubview:_instructorName];
        
    _timeLabel = [[UILabel alloc] initWithFrame:CGRectMake(12, 61, 100, 15)];
    _timeLabel.textColor = [UIColor whiteColor];
    _timeLabel.font = [UIFont fontWithName:@"HelveticaNeue" size:12];
    [self addSubview:_timeLabel];
        
    _attendanceStatus = [[UIImageView alloc] initWithFrame:CGRectMake(261, 28, 32, 32)];
    UIImage *attendanceMark = [UIImage imageNamed:@"Present"];
    [_attendanceStatus setImage:attendanceMark];
    [self addSubview:_attendanceStatus];
    [self setSelectionStyle:UITableViewCellSelectionStyleNone];
    }
    return self;
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];
}

@end
