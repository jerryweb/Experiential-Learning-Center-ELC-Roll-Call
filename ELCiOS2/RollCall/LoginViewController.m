//
//  LoginViewController.m
//  RollCall
//
//  Created by Eric Wang on 2/26/16.
//  Copyright © 2016 Eric Wang. All rights reserved.
//

#import "LoginViewController.h"
#import "StudentHomePageViewController.h"
#import "AdminHomePageViewController.h"
#import "SignupViewController.h"
#import "ForgotPasswordViewController.h"
#import <QuartzCore/QuartzCore.h>
#import <Firebase/Firebase.h>

@interface LoginViewController () <UITextFieldDelegate>

@property (nonatomic,strong) UILabel *titleLabel;
@property (nonatomic,strong) UITextField *emailField;
@property (nonatomic,strong) UITextField *passwordField;
@property (nonatomic,strong) UIButton *forgotPasswordButton;
@property (nonatomic,strong) UIButton *loginButton;
@property (nonatomic,strong) UIButton *registerButton;

@end

@implementation LoginViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    // config title
    _titleLabel = [[UILabel alloc] initWithFrame:CGRectMake(126, 100, 123, 43)];
    _titleLabel.text = @"RollCall";
    _titleLabel.textColor = [UIColor whiteColor];
    _titleLabel.font = [UIFont fontWithName:@"HelveticaNeue" size:36];
    
    // config email field
    _emailField = [[UITextField alloc] initWithFrame:CGRectMake(79, 203, 218, 23)];
    _emailField.text = @"Email Address";
    _emailField.textColor = [UIColor whiteColor];
    _emailField.font = [UIFont fontWithName:@"HelveticaNeue" size:20];
    _emailField.delegate = self;
    _emailField.tintColor = [UIColor clearColor];
    _emailField.autocapitalizationType = UITextAutocapitalizationTypeNone;
    
    // add  divider
    UIView *dividerView = [[UIView alloc] initWithFrame:CGRectMake(79, 242, 218, .4)];
    dividerView.backgroundColor = [UIColor whiteColor];
    
    // config password field
    _passwordField = [[UITextField alloc] initWithFrame:CGRectMake(79, 257, 218, 23)];
    _passwordField.text = @"Password";
    _passwordField.textColor = [UIColor whiteColor];
    _passwordField.font = [UIFont fontWithName:@"HelveticaNeue" size:20];
    _passwordField.delegate = self;
    _passwordField.tintColor = [UIColor clearColor];
    _passwordField.autocapitalizationType = UITextAutocapitalizationTypeNone;
    //_passwordField.secureTextEntry = YES;

    // config forgot password button
    _forgotPasswordButton = [[UIButton alloc]initWithFrame:CGRectMake(79, 295, 130, 15)];
    [_forgotPasswordButton setTitle:@"Forgot Your Password?" forState:UIControlStateNormal];
    [_forgotPasswordButton setTitleColor:[UIColor colorWithRed:255/255.0 green:255/255.0 blue:255/255.0 alpha:1.0] forState:UIControlStateNormal];
    [_forgotPasswordButton addTarget:self action:@selector(openForgotPassword:) forControlEvents:UIControlEventTouchUpInside];
    _forgotPasswordButton.titleLabel.font = [UIFont fontWithName:@"HelveticaNeue" size:12];
    
    // config login button
    _loginButton = [[UIButton alloc] initWithFrame:CGRectMake(79, 332, 218, 36)];
    [_loginButton setTitle:@"Log In" forState:UIControlStateNormal];
    [_loginButton setTitleColor:[UIColor colorWithRed:255/255.0 green:255/255.0 blue:255/255.0 alpha:1.0] forState:UIControlStateNormal];
    [_loginButton setBackgroundColor:[UIColor colorWithRed:80/255.0 green:227/255.0 blue:194/255.0 alpha:1.0]];
    _loginButton.layer.cornerRadius = 10;
    _loginButton.clipsToBounds = YES;
    [_loginButton addTarget:self action:@selector(login:) forControlEvents:UIControlEventTouchUpInside];
    
    // config register button
    _registerButton = [[UIButton alloc] initWithFrame:CGRectMake(79, 388, 218, 36)];
    [_registerButton setTitle:@"Register" forState:UIControlStateNormal];
    [_registerButton setTitleColor:[UIColor colorWithRed:255/255.0 green:255/255.0 blue:255/255.0 alpha:1.0] forState:UIControlStateNormal];
    [_registerButton setBackgroundColor:[UIColor colorWithRed:80/255.0 green:227/255.0 blue:194/255.0 alpha:1.0]];
    _registerButton.layer.cornerRadius = 10;
    _registerButton.clipsToBounds = YES;
    [_registerButton addTarget:self action:@selector(newUserRegister:) forControlEvents:UIControlEventTouchUpInside];

    // add subivews
    [self.view addSubview:_titleLabel];
    [self.view addSubview:_emailField];
    [self.view addSubview:dividerView];
    [self.view addSubview:_passwordField];
    [self.view addSubview:_forgotPasswordButton];
    [self.view addSubview:_loginButton];
    [self.view addSubview:_registerButton];
}

- (void)newUserRegister:(UIButton *)sender {
    SignupViewController *svc = [[SignupViewController alloc] init];
    [self.navigationController pushViewController:svc animated:NO];
}

- (void)openForgotPassword:(UIButton *)sender {
    ForgotPasswordViewController *fvc = [[ForgotPasswordViewController alloc] init];
    [self.navigationController pushViewController:fvc animated:NO];
}

- (void)login:(UIButton *)sender {
    //StudentHomePageViewController *ivc = [[StudentHomePageViewController alloc] init];
    //[self.navigationController pushViewController:ivc animated:NO];
    AdminHomePageViewController *avc = [[AdminHomePageViewController alloc] init];
    [self.navigationController pushViewController:avc animated:NO];
}

- (void)textFieldDidBeginEditing:(UITextField *)textField {
    [textField setText:@""];
    textField.autocorrectionType = UITextAutocorrectionTypeNo;
    if(textField == _passwordField) {
        _passwordField.secureTextEntry = YES;
    }
}

@end
